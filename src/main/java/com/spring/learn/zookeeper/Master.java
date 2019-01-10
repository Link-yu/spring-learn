package com.spring.learn.zookeeper;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import static org.apache.zookeeper.ZooDefs.Ids.OPEN_ACL_UNSAFE;

public class Master implements Watcher {
    ZooKeeper zooKeeper;
    String hostPort;
    String serverId = Integer.toString((int)Math.random()*10);
    boolean isLeader = false;
    AsyncCallback.StringCallback callback = new AsyncCallback.StringCallback() {
        @Override
        public void processResult(int i, String s, Object o, String s1) {
            switch (KeeperException.Code.get(i)) {
                case CONNECTIONLOSS:
                    checkMaster();
                    return;
                case OK:
                    isLeader = true;
                    break;
                    default:
                        isLeader = true;
            }

            System.out.println("I’m" + (isLeader ? "": "not") + "the leader!");
        }
    };

    AsyncCallback.DataCallback dataCallback = new AsyncCallback.DataCallback() {
        @Override
        public void processResult(int i, String s, Object o, byte[] bytes, Stat stat) {
            switch (KeeperException.Code.get(i)){
                case CONNECTIONLOSS:
                    checkMaster();
                    return;
                case NONODE:
                    runForMasterAsync();
                    return;
            }
        }
    };

    public AsyncCallback.StringCallback createParentCallback = new AsyncCallback.StringCallback() {
        @Override
        public void processResult(int i, String s, Object o, String s1) {
            switch (KeeperException.Code.get(i)){
                case CONNECTIONLOSS:
                    createParent(s, (byte[]) o);
                    break;
                case OK:
                    break;
                case NODEEXISTS:
                    break;
                    default:
            }
        }
    };
    public void bootstrap() {
        createParent("/workers", new byte[0]);
        createParent("/assign", new byte[0]);
        createParent("/tasks", new byte[0]);
        createParent("/stat", new byte[0]);
    }

    public void createParent(String path, byte[] data) {
        zooKeeper.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT, createParentCallback , data);
    }
    public Master(String hostPort) {
        this.hostPort = hostPort;
    }

    public void startZk() throws Exception {
        zooKeeper = new ZooKeeper(hostPort, 15000, this);
    }

    public void stopZk() throws Exception {
        zooKeeper.close();
    }
    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println(watchedEvent);
    }

    public void runForMaster() throws Exception {
        while(true) {
            try {
                zooKeeper.create("/master", serverId.getBytes(), OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                isLeader = true;
                break;
            } catch (Exception e) {
                isLeader = false;
                break;
            }
        }
    }

    public void runForMasterAsync() {
        zooKeeper.create("/master", serverId.getBytes(), OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL, callback, null);
    }

    public void checkMasterAsync() {
        zooKeeper.getData("/master", false, dataCallback, null);
    }
    boolean checkMaster() {
        while(true) {
            try {
                Stat stat = new Stat();
                byte data[] = zooKeeper.getData("/master",false, stat);
                isLeader = new String(data).equals(serverId);
                return true;
            } catch (Exception E) {
                return false;
            }
        }
    }
    public static void main(String[] args) throws Exception{
        Master m = new Master("127.0.0.1:2182");
        m.startZk();
        m.runForMaster();
        if (m.checkMaster()) {
            System.out.println("I'm the leader!");
            Thread.sleep(60000);
        } else {
            System.out.println("someone is the leader!");
        }
        m.stopZk();
    }
}
