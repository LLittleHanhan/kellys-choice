# 快速启动指南

## 🚀 5分钟快速部署

### 环境要求
- Docker 20.10+
- Docker Compose 2.0+
- 至少4GB内存
- 至少10GB磁盘空间

### 1. 克隆项目
```bash
git clone <repository-url>
cd review-platform
```

### 2. 一键启动
```bash
# 启动所有服务
docker-compose up -d

# 等待服务启动（约2-3分钟）
```

### 3. 访问应用
- **前端**: http://localhost:3000
- **后端API**: http://localhost:8080/api
- **数据库管理**: localhost:3306
- **Redis**: localhost:6379
- **Elasticsearch**: http://localhost:9200
- **RabbitMQ管理**: http://localhost:15672

### 4. 测试账号
- **用户名**: admin
- **密码**: 123456

## 📋 功能测试

### 用户功能
1. 访问 http://localhost:3000
2. 点击"注册"创建新账号
3. 登录后可以浏览清洁公司
4. 点击公司查看详情
5. 发布清洁服务点评和评分

### 管理功能
1. 访问 http://localhost:8080/admin
2. 使用admin账号登录
3. 管理用户、清洁公司、点评

## 🔧 开发环境

### 前端开发
```bash
cd frontend
npm install
npm start
```

### 后端开发
```bash
cd backend
# 需要安装Java 17和Maven
mvn spring-boot:run
```

### 数据库连接
```bash
# MySQL连接信息
主机: localhost
端口: 3306
数据库: review_platform
用户名: review_user
密码: review123456
```

## 📊 监控和日志

### 查看服务状态
```bash
docker-compose ps
```

### 查看日志
```bash
# 查看所有服务日志
docker-compose logs -f

# 查看特定服务日志
docker-compose logs -f backend
docker-compose logs -f frontend
```

### 健康检查
```bash
# 后端健康检查
curl http://localhost:8080/api/actuator/health

# Elasticsearch健康检查
curl http://localhost:9200/_cluster/health
```

## 🛠️ 常见问题

### 1. 端口被占用
```bash
# 检查端口占用
netstat -tulpn | grep :8080

# 修改docker-compose.yml中的端口映射
```

### 2. 内存不足
```bash
# 增加Docker内存限制
# 在Docker Desktop中调整内存设置到4GB以上
```

### 3. 数据库连接失败
```bash
# 重启MySQL服务
docker-compose restart mysql

# 查看MySQL日志
docker-compose logs mysql
```

### 4. 前端无法访问
```bash
# 重启前端服务
docker-compose restart frontend

# 检查前端日志
docker-compose logs frontend
```

## 📈 性能优化

### 开发环境优化
```bash
# 限制资源使用
docker-compose up -d --scale backend=1 --scale frontend=1
```

### 生产环境优化
```bash
# 使用生产环境配置
docker-compose -f docker-compose.prod.yml up -d
```

## 🔒 安全配置

### 修改默认密码
```bash
# 修改MySQL密码
docker exec -it review-mysql mysql -u root -p
ALTER USER 'review_user'@'%' IDENTIFIED BY 'new_password';

# 修改Redis密码（如需要）
# 在docker-compose.yml中配置Redis密码
```

### 配置SSL证书
```bash
# 安装Certbot
sudo apt install certbot

# 获取SSL证书
sudo certbot certonly --standalone -d yourdomain.com
```

## 📚 相关文档

- [项目概述](./docs/project-overview.md)
- [部署指南](./docs/deployment.md)
- [服务器配置](./docs/server-domain-guide.md)
- [API文档](./docs/api.md)

## 🆘 获取帮助

### 技术支持
- 查看日志: `docker-compose logs`
- 重启服务: `docker-compose restart`
- 重新构建: `docker-compose build`

### 联系方式
- 项目Issues: [GitHub Issues]
- 技术交流: [技术群]
- 邮件支持: support@example.com

## 🎯 下一步

1. **自定义配置**
   - 修改数据库密码
   - 配置域名和SSL
   - 调整性能参数

2. **功能扩展**
   - 添加在线预约功能
   - 集成地图服务
   - 开发移动端

3. **生产部署**
   - 购买云服务器
   - 配置域名解析
   - 设置监控告警

---

**祝您使用愉快！** 🎉