# 部署指南

## 环境要求

### 开发环境
- **操作系统**: Linux/macOS/Windows
- **Docker**: 20.10+
- **Docker Compose**: 2.0+
- **内存**: 至少4GB RAM
- **磁盘空间**: 至少10GB可用空间

### 生产环境
- **服务器**: Ubuntu 20.04+ / CentOS 8+
- **CPU**: 4核心以上
- **内存**: 8GB RAM以上
- **磁盘**: 50GB SSD
- **网络**: 稳定的互联网连接

## 快速部署

### 1. 克隆项目
```bash
git clone <repository-url>
cd review-platform
```

### 2. 配置环境变量
```bash
# 复制环境变量模板
cp .env.example .env

# 编辑环境变量
vim .env
```

### 3. 启动服务
```bash
# 使用部署脚本
./scripts/deploy.sh

# 或手动启动
docker-compose up -d
```

### 4. 验证部署
```bash
# 检查服务状态
docker-compose ps

# 查看日志
docker-compose logs -f
```

## 服务访问地址

| 服务 | 地址 | 说明 |
|------|------|------|
| 前端应用 | http://localhost:3000 | React前端 |
| 后端API | http://localhost:8080/api | Spring Boot API |
| MySQL数据库 | localhost:3306 | 主数据库 |
| Redis缓存 | localhost:6379 | 缓存服务 |
| Elasticsearch | http://localhost:9200 | 搜索引擎 |
| RabbitMQ管理 | http://localhost:15672 | 消息队列管理 |

## 数据库配置

### MySQL
- **数据库名**: review_platform
- **用户名**: review_user
- **密码**: review123456
- **端口**: 3306

### Redis
- **端口**: 6379
- **无密码**

### Elasticsearch
- **端口**: 9200
- **无认证**

### RabbitMQ
- **管理端口**: 15672
- **用户名**: admin
- **密码**: admin123456

## 生产环境部署

### 1. 服务器准备
```bash
# 更新系统
sudo apt update && sudo apt upgrade -y

# 安装Docker
curl -fsSL https://get.docker.com -o get-docker.sh
sudo sh get-docker.sh

# 安装Docker Compose
sudo curl -L "https://github.com/docker/compose/releases/download/v2.20.0/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose
```

### 2. 域名配置
```bash
# 配置域名解析
# 将你的域名解析到服务器IP

# 配置SSL证书（可选）
# 使用Let's Encrypt免费证书
```

### 3. 环境变量配置
```bash
# 生产环境配置
export NODE_ENV=production
export SPRING_PROFILES_ACTIVE=prod
```

### 4. 启动服务
```bash
# 使用生产环境配置
docker-compose -f docker-compose.prod.yml up -d
```

## 监控和维护

### 日志查看
```bash
# 查看所有服务日志
docker-compose logs -f

# 查看特定服务日志
docker-compose logs -f backend
docker-compose logs -f frontend
```

### 数据备份
```bash
# MySQL备份
docker exec review-mysql mysqldump -u review_user -preview123456 review_platform > backup.sql

# Redis备份
docker exec review-redis redis-cli BGSAVE
```

### 服务重启
```bash
# 重启所有服务
docker-compose restart

# 重启特定服务
docker-compose restart backend
```

## 故障排除

### 常见问题

1. **端口冲突**
   ```bash
   # 检查端口占用
   netstat -tulpn | grep :8080
   
   # 修改docker-compose.yml中的端口映射
   ```

2. **内存不足**
   ```bash
   # 增加Docker内存限制
   # 在Docker Desktop中调整内存设置
   ```

3. **数据库连接失败**
   ```bash
   # 检查MySQL容器状态
   docker-compose logs mysql
   
   # 重启MySQL
   docker-compose restart mysql
   ```

### 性能优化

1. **增加缓存**
   - 配置Redis缓存策略
   - 启用Elasticsearch缓存

2. **数据库优化**
   - 添加数据库索引
   - 优化查询语句

3. **前端优化**
   - 启用Gzip压缩
   - 配置CDN

## 安全配置

### 防火墙设置
```bash
# 只开放必要端口
sudo ufw allow 80
sudo ufw allow 443
sudo ufw allow 22
```

### SSL证书
```bash
# 使用Let's Encrypt
sudo apt install certbot
sudo certbot certonly --standalone -d yourdomain.com
```

### 数据库安全
- 修改默认密码
- 限制数据库访问IP
- 定期备份数据 