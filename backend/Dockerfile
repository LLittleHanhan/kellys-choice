FROM openjdk:17-jdk-slim

WORKDIR /app

# 复制Maven配置文件
COPY pom.xml .

# 安装Maven
RUN apt-get update && apt-get install -y maven

# 复制源代码
COPY src ./src

# 构建应用
RUN mvn clean package -DskipTests

# 创建上传目录
RUN mkdir -p /app/uploads

# 暴露端口
EXPOSE 8080

# 启动应用
ENTRYPOINT ["java", "-jar", "target/*.jar"] 