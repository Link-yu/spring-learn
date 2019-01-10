package com.spring.learn.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;

public class Worker implements Watcher {
    private String hostPort;
    private ZooKeeper zooKeeper;
    private String serverId = Integer.toString((int)Math.random());

    public Worker(String hostPort) {
        this.hostPort = hostPort;
    }

    public void startZK() throws IOException {
        zooKeeper = new ZooKeeper(hostPort,15000, this);
    }
    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println(watchedEvent + hostPort);
    }

    public void register() {
        zooKeeper.create("/workers/worker-" + serverId, "Idle".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL,createWorkerCallback ,null);
    }

    AsyncCallback.StringCallback createWorkerCallback = new AsyncCallback.StringCallback() {
        @Override
        public void processResult(int i, String s, Object o, String s1) {
            switch (KeeperException.Code.get(i)) {
                case CONNECTIONLOSS:
                    register();
                    break;
                case OK:
                    break;
                case NODEEXISTS:
                    break;
                    default:
                        break;
            }
        }
    };

    public static void main(String[] args) throws Exception {
        Worker w = new Worker("127.0.0.1:2182");
        w.startZK();
        w.register();
        Thread.sleep(30000);
    }
}
