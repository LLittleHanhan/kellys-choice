# 清洁公司点评平台 (Cleaning Company Review Platform)

## 项目简介
这是一个专业的清洁公司点评平台，采用现代化的技术栈构建，支持用户注册、登录、发布清洁服务点评、搜索、推荐等功能。平台专注于本地清洁公司的评价和服务质量监督。

## 技术栈

### 前端
- **React 18** - 现代化的前端框架
- **TypeScript** - 类型安全的JavaScript
- **Redux Toolkit** - 状态管理
- **Ant Design** - UI组件库
- **Axios** - HTTP客户端
- **React Router** - 路由管理

### 后端
- **Java 17** - 编程语言
- **Spring Boot 3.x** - 应用框架
- **Spring Security** - 安全认证
- **Spring Data JPA** - 数据访问
- **MySQL 8.0** - 主数据库
- **Redis** - 缓存和会话存储
- **Elasticsearch** - 搜索引擎
- **RabbitMQ** - 消息队列

### 部署
- **Docker** - 容器化
- **Docker Compose** - 多容器编排
- **Nginx** - 反向代理

## 项目结构
```
review-platform/
├── frontend/          # React前端应用
├── backend/           # Spring Boot后端应用
├── docker/            # Docker配置文件
├── docs/              # 项目文档
├── scripts/           # 部署脚本
└── README.md          # 项目说明
```

## 快速开始

### 环境要求
- Node.js 18+
- Java 17+
- Docker & Docker Compose
- MySQL 8.0
- Redis 6.0+
- Elasticsearch 8.x

### 启动项目
```bash
# 克隆项目
git clone <repository-url>
cd review-platform

# 启动所有服务
docker-compose up -d

# 访问应用
# 前端: http://localhost:3000
# 后端API: http://localhost:8080/api
# 管理后台: http://localhost:8080/admin
```

## 功能特性
- 用户注册/登录/认证
- 清洁公司信息管理
- 用户清洁服务点评发布
- 评分系统（1-5星）
- 服务类型分类（日常保洁/深度清洁/开荒保洁等）
- 服务区域筛选
- 价格信息展示
- 搜索功能
- 推荐系统
- 图片上传
- 评论管理
- 数据统计

## 核心业务

### 清洁公司管理
- 公司基本信息（名称、地址、电话）
- 服务区域管理
- 营业执照验证
- 服务类型配置
- 营业时间设置

### 用户点评系统
- 服务类型选择
- 服务日期记录
- 价格信息记录
- 评分和评价
- 图片上传

### 搜索和筛选
- 按服务区域搜索
- 按服务类型筛选
- 按评分排序
- 按价格范围筛选

## 开发指南
详细开发文档请参考 `docs/` 目录。 