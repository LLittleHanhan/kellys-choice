#!/bin/bash

# 点评网站部署脚本

echo "开始部署点评网站..."

# 检查Docker是否安装
if ! command -v docker &> /dev/null; then
    echo "错误: Docker未安装，请先安装Docker"
    exit 1
fi

# 检查Docker Compose是否安装
if ! command -v docker-compose &> /dev/null; then
    echo "错误: Docker Compose未安装，请先安装Docker Compose"
    exit 1
fi

# 停止现有容器
echo "停止现有容器..."
docker-compose down

# 构建镜像
echo "构建Docker镜像..."
docker-compose build

# 启动服务
echo "启动服务..."
docker-compose up -d

# 等待服务启动
echo "等待服务启动..."
sleep 30

# 检查服务状态
echo "检查服务状态..."
docker-compose ps

echo "部署完成！"
echo "前端地址: http://localhost:3000"
echo "后端API: http://localhost:8080/api"
echo "管理后台: http://localhost:8080/admin"
echo ""
echo "数据库管理:"
echo "MySQL: localhost:3306 (用户名: review_user, 密码: review123456)"
echo "Redis: localhost:6379"
echo "Elasticsearch: http://localhost:9200"
echo "RabbitMQ管理: http://localhost:15672 (用户名: admin, 密码: admin123456)" 