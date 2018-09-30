# spring-learn
9.28 集成mybatis
```
问题：启动后调用UserDao报错，没有找到mapper.xml
原因：没有在application.yml中配置mapper.xml的位置
mybatis:
  mapperLocations: classpath:mapper/*.xml
  typeAliasesPackage: tk.mapper.model
```
9.29 继承springboot自带的log4j功能
```
实现日志打印
logging:
  path: E:\\demo //将日志打印在E:demo目录下,名为spring.log的日志
  file: log\sp.log // 相对路径,将会在代码目录下log文件夹下打印sp.log
  level:
    com:
      spring:
        learn:
          Dao: debug //打印日志级别,其中com: spring:learn:Dao指mapper文件包名,打印操作数据库的日志
```