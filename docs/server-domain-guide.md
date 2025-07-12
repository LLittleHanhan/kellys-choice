# 服务器和域名配置指南

## 服务器配置

### 推荐配置

#### 开发/测试环境
- **CPU**: 2核心
- **内存**: 4GB RAM
- **存储**: 20GB SSD
- **带宽**: 5Mbps
- **操作系统**: Ubuntu 20.04 LTS

#### 生产环境
- **CPU**: 4核心以上
- **内存**: 8GB RAM以上
- **存储**: 50GB SSD以上
- **带宽**: 10Mbps以上
- **操作系统**: Ubuntu 20.04 LTS / CentOS 8

### 云服务器推荐

#### 国内服务器
1. **阿里云**
   - 轻量应用服务器
   - 2核4GB配置约100元/月
   - 适合中小型项目

2. **腾讯云**
   - 云服务器CVM
   - 2核4GB配置约90元/月
   - 稳定性好

3. **华为云**
   - 弹性云服务器
   - 2核4GB配置约120元/月
   - 安全性高

#### 国外服务器
1. **AWS EC2**
   - t3.medium (2核4GB)
   - 约$30/月
   - 全球部署

2. **DigitalOcean**
   - Droplet 4GB
   - 约$24/月
   - 简单易用

3. **Vultr**
   - 4GB配置
   - 约$20/月
   - 性价比高

## 域名配置

### 域名购买

#### 国内域名商
1. **阿里云万网**
   - 价格实惠
   - 备案方便
   - 推荐域名：.com, .cn

2. **腾讯云**
   - 服务稳定
   - 管理简单
   - 推荐域名：.com, .net

#### 国外域名商
1. **GoDaddy**
   - 全球最大域名商
   - 价格便宜
   - 推荐域名：.com, .org

2. **Namecheap**
   - 价格实惠
   - 服务好
   - 推荐域名：.com, .net

### 域名选择建议

#### 推荐域名后缀
- **.com** - 最通用，用户信任度高
- **.cn** - 中国用户熟悉
- **.net** - 技术类网站常用
- **.org** - 组织类网站

#### 域名命名建议
- 简短易记
- 避免数字和连字符
- 体现业务特点
- 示例：
  - reviewhub.com
  - foodreview.cn
  - reviewplatform.net

## 服务器部署步骤

### 1. 服务器准备

```bash
# 更新系统
sudo apt update && sudo apt upgrade -y

# 安装必要工具
sudo apt install -y curl wget git vim htop

# 设置时区
sudo timedatectl set-timezone Asia/Shanghai
```

### 2. 安装Docker

```bash
# 安装Docker
curl -fsSL https://get.docker.com -o get-docker.sh
sudo sh get-docker.sh

# 启动Docker服务
sudo systemctl start docker
sudo systemctl enable docker

# 将当前用户添加到docker组
sudo usermod -aG docker $USER
```

### 3. 安装Docker Compose

```bash
# 安装Docker Compose
sudo curl -L "https://github.com/docker/compose/releases/download/v2.20.0/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose

# 验证安装
docker-compose --version
```

### 4. 配置防火墙

```bash
# 安装UFW防火墙
sudo apt install ufw

# 配置防火墙规则
sudo ufw allow 22/tcp    # SSH
sudo ufw allow 80/tcp    # HTTP
sudo ufw allow 443/tcp   # HTTPS
sudo ufw allow 8080/tcp  # 应用端口

# 启用防火墙
sudo ufw enable
```

## 域名解析配置

### 1. 域名解析设置

在域名管理后台添加以下DNS记录：

```
类型: A
主机记录: @
记录值: 你的服务器IP地址
TTL: 600

类型: A
主机记录: www
记录值: 你的服务器IP地址
TTL: 600
```

### 2. 验证解析

```bash
# 检查域名解析
nslookup yourdomain.com
dig yourdomain.com

# 测试连接
curl -I http://yourdomain.com
```

## SSL证书配置

### 1. 安装Certbot

```bash
# 安装Certbot
sudo apt install certbot

# 获取SSL证书
sudo certbot certonly --standalone -d yourdomain.com -d www.yourdomain.com
```

### 2. 配置Nginx SSL

```nginx
server {
    listen 443 ssl http2;
    server_name yourdomain.com www.yourdomain.com;

    ssl_certificate /etc/letsencrypt/live/yourdomain.com/fullchain.pem;
    ssl_certificate_key /etc/letsencrypt/live/yourdomain.com/privkey.pem;

    # SSL配置
    ssl_protocols TLSv1.2 TLSv1.3;
    ssl_ciphers ECDHE-RSA-AES128-GCM-SHA256:ECDHE-RSA-AES256-GCM-SHA384;
    ssl_prefer_server_ciphers off;

    # 其他配置...
}
```

### 3. 自动续期

```bash
# 创建续期脚本
sudo crontab -e

# 添加以下行
0 12 * * * /usr/bin/certbot renew --quiet
```

## 生产环境优化

### 1. 系统优化

```bash
# 优化内核参数
echo 'net.core.somaxconn = 65535' >> /etc/sysctl.conf
echo 'net.ipv4.tcp_max_syn_backlog = 65535' >> /etc/sysctl.conf
sysctl -p

# 优化文件描述符限制
echo '* soft nofile 65535' >> /etc/security/limits.conf
echo '* hard nofile 65535' >> /etc/security/limits.conf
```

### 2. Docker优化

```bash
# 配置Docker守护进程
sudo mkdir -p /etc/docker
sudo tee /etc/docker/daemon.json <<-'EOF'
{
  "log-driver": "json-file",
  "log-opts": {
    "max-size": "10m",
    "max-file": "3"
  },
  "storage-driver": "overlay2"
}
EOF

# 重启Docker
sudo systemctl restart docker
```

### 3. 监控配置

```bash
# 安装监控工具
sudo apt install -y htop iotop nethogs

# 配置日志轮转
sudo tee /etc/logrotate.d/docker <<-'EOF'
/var/lib/docker/containers/*/*.log {
    rotate 7
    daily
    compress
    size=1M
    missingok
    delaycompress
    copytruncate
}
EOF
```

## 备份策略

### 1. 数据库备份

```bash
# 创建备份脚本
sudo tee /opt/backup.sh <<-'EOF'
#!/bin/bash
BACKUP_DIR="/opt/backups"
DATE=$(date +%Y%m%d_%H%M%S)

# 创建备份目录
mkdir -p $BACKUP_DIR

# MySQL备份
docker exec review-mysql mysqldump -u review_user -preview123456 review_platform > $BACKUP_DIR/mysql_$DATE.sql

# Redis备份
docker exec review-redis redis-cli BGSAVE

# 压缩备份文件
tar -czf $BACKUP_DIR/backup_$DATE.tar.gz $BACKUP_DIR/mysql_$DATE.sql

# 删除7天前的备份
find $BACKUP_DIR -name "backup_*.tar.gz" -mtime +7 -delete
EOF

# 设置执行权限
sudo chmod +x /opt/backup.sh

# 添加到定时任务
sudo crontab -e
# 添加：0 2 * * * /opt/backup.sh
```

### 2. 应用备份

```bash
# 备份Docker镜像
docker save review-platform-backend:latest > /opt/backups/backend_$(date +%Y%m%d).tar
docker save review-platform-frontend:latest > /opt/backups/frontend_$(date +%Y%m%d).tar
```

## 成本估算

### 月度成本（人民币）

#### 小型项目（1000用户以下）
- **服务器**: 阿里云轻量应用服务器 2核4GB - 100元/月
- **域名**: .com域名 - 60元/年（5元/月）
- **SSL证书**: Let's Encrypt免费
- **总计**: 约105元/月

#### 中型项目（1000-10000用户）
- **服务器**: 阿里云ECS 4核8GB - 300元/月
- **域名**: .com域名 - 60元/年（5元/月）
- **CDN**: 阿里云CDN - 100元/月
- **总计**: 约405元/月

#### 大型项目（10000用户以上）
- **服务器**: 阿里云ECS 8核16GB - 800元/月
- **域名**: .com域名 - 60元/年（5元/月）
- **CDN**: 阿里云CDN - 300元/月
- **数据库**: RDS MySQL - 500元/月
- **总计**: 约1605元/月

## 安全建议

### 1. 服务器安全
- 定期更新系统补丁
- 使用强密码
- 禁用root远程登录
- 配置SSH密钥认证

### 2. 应用安全
- 启用HTTPS
- 配置WAF防火墙
- 定期备份数据
- 监控异常访问

### 3. 域名安全
- 启用域名锁定
- 配置域名隐私保护
- 定期检查DNS解析
- 设置域名到期提醒

## 故障排除

### 常见问题

1. **域名无法访问**
   - 检查DNS解析
   - 验证防火墙设置
   - 确认服务状态

2. **SSL证书问题**
   - 检查证书有效期
   - 验证域名匹配
   - 重新申请证书

3. **性能问题**
   - 检查服务器资源
   - 优化数据库查询
   - 配置CDN加速

### 联系支持

- **阿里云**: 400-801-0810
- **腾讯云**: 400-9100-1000
- **华为云**: 400-822-9999 