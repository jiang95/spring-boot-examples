# SpringBoot
Spring Boot项目。配置了druid+mybatis+redis。
- 使用了pagehelper的分页插件
- 日志使用logback。
- 使用maven实现开发/测试环境多环境

## 打成jar包
```bash
cd 项目跟目录（和pom.xml同级）
mvn clean package
## 或者执行下面的命令
## 排除测试代码后进行打包
mvn clean package  -Dmaven.test.skip=true
```
## 启动jar
```bash
java -jar  target/项目名称-版本号.jar
```

## RabbitMq
项目整合了RabbitMq。代码在rabbitmq分支上面

## kafka
代码在kafka分支上，正在整合中。。。。。

## redis
项目整合了Redis.

## docker
docker部署代码在docker分支上面。

## 七牛云
图片上传整合了七牛云

## 测试环境/正式环境
使用profiles实现了环境分开

## MongoDB
在分支mongo上

## elasticsearch
在分支elasticsearch上

## jib
在分支jib上面
Jib 是 Google 开发的可以直接构建 Java 应用的 Docker 和 OCI 镜像的类库，以 Maven 和 Gradle 插件形式提供。
- [官网GitHub](https://github.com/GoogleContainerTools/jib)