FROM openjdk:17-jdk-slim

WORKDIR /app

# 复制Maven配置文件
COPY pom.xml .
COPY src ./src

# 安装Maven并构建应用
RUN apt-get update && apt-get install -y maven
RUN mvn clean package -DskipTests

# 复制构建好的jar文件
COPY target/*.jar app.jar

# 创建上传目录
RUN mkdir -p /app/uploads

# 暴露端口
EXPOSE 8080

# 启动应用
ENTRYPOINT ["java", "-jar", "app.jar"] 