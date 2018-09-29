# spring-learn
9.28 集成mybatis
```
问题：启动后调用UserDao报错，没有找到mapper.xml
原因：没有在application.yml中配置mapper.xml的位置
mybatis:
  mapperLocations: classpath:mapper/*.xml
  typeAliasesPackage: tk.mapper.model
```
