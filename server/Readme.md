# Document of this project

### 功能

* 增删改查
* 文件上传
* 数据库配置
* 日志管理
* 权限控制

### 后端运行流程

1. 配置端口 位于application.yml
2. 配置DB_NAME 位于application.yml
3. 配置BASE_LOCATION 位于application.yml
4. 修改logback-spring.xml下的LOG_HOME的value值
5. maven clean -> maven compile
6. 点击run按钮

### 运行jar命令

title xxxx
java -jar -Xms64m -Xmx128m -XX:MetaspaceSize=64m -XX:MaxMetaspaceSize=64m xxxxx.jar


### 数据库相关

删除数据库命令：

drop database if exists xxx;

创建数据库命令：

CREATE DATABASE IF NOT EXISTS xxx DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

数据库备份命令:

mysqldump -u root -p --databases 数据库名称 > xxx.sql

数据库恢复命令：

source D:\\xxx.sql


### 协同过滤推荐算法

该系统的热门推荐模块，使用的算法是协同过滤推荐算法，大体流程是：记录每个用户浏览的物件id，并记录到b_record表里面（ThingController里面的detail方法），
每次浏览一次分数加1； 推荐的时候，调用协同过滤方法（ThingController里面的recommend方法），为用户推荐物品。

参考文献：

https://blog.csdn.net/net19880504/article/details/137773127


### 常见问题

#### 日志路径

修改logback-spring.xml下的LOG_HOME的value值

#### 配置数据库

application.yml中

#### Druid连接池配置

* application.yml中配置druid
* 参考链接：https://blog.csdn.net/nothingavenger/article/details/114119585
* 监控地址：http://localhost:8009/druid/index.html

#### mysql主键id过长

https://blog.csdn.net/qq_46728644/article/details/120772577

#### yml不起作用

需要maven clean一下

#### 注意实体字段最好是String类型

实体字段最好是String类型，mybatis-plus的update的时候，null的不更新

#### 打包步骤

maven clean -> maven package

https://blog.csdn.net/weixin_42822484/article/details/107893586

#### 配置文件上传大小

application.yml中multipart下

#### 静态资源路径配置

https://blog.csdn.net/cylcjsg/article/details/128102776?

#### 跨域配置

见CorsConfig.java





