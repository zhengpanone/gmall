## 构建运行本地镜像
```shell
mvn -pl member-server clean package jib:dockerBuild -DskipTests
docker run -d -p 48082:48082 member-server:1.0.0
```