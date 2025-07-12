# 点评网站项目概述

## 项目简介

这是一个企业级点评网站项目，采用现代化的技术栈构建，支持用户注册、登录、发布点评、搜索、推荐等功能。项目采用前后端分离架构，使用Docker容器化部署。

## 技术架构

### 前端技术栈
- **React 18** - 现代化的前端框架
- **TypeScript** - 类型安全的JavaScript
- **Redux Toolkit** - 状态管理
- **Ant Design** - UI组件库
- **React Router** - 路由管理
- **Axios** - HTTP客户端

### 后端技术栈
- **Java 17** - 编程语言
- **Spring Boot 3.x** - 应用框架
- **Spring Security** - 安全认证
- **Spring Data JPA** - 数据访问
- **MySQL 8.0** - 主数据库
- **Redis** - 缓存和会话存储
- **Elasticsearch** - 搜索引擎
- **RabbitMQ** - 消息队列

### 部署技术
- **Docker** - 容器化
- **Docker Compose** - 多容器编排
- **Nginx** - 反向代理

## 功能特性

### 用户功能
- ✅ 用户注册/登录
- ✅ 用户认证和授权
- ✅ 个人资料管理
- ✅ 我的点评查看

### 商家功能
- ✅ 商家信息展示
- ✅ 商家搜索和筛选
- ✅ 商家详情页面
- ✅ 地理位置搜索

### 点评功能
- ✅ 发布点评
- ✅ 评分系统（1-5星）
- ✅ 点评内容管理
- ✅ 图片上传
- ✅ 点赞功能

### 搜索功能
- ✅ 关键词搜索
- ✅ 分类筛选
- ✅ 地理位置搜索
- ✅ 评分筛选

### 管理功能
- ✅ 用户管理
- ✅ 商家管理
- ✅ 点评审核
- ✅ 数据统计

## 项目结构

```
review-platform/
├── frontend/                 # React前端应用
│   ├── src/
│   │   ├── components/      # 组件
│   │   ├── pages/          # 页面
│   │   ├── store/          # Redux状态管理
│   │   ├── services/       # API服务
│   │   └── types/          # TypeScript类型定义
│   ├── package.json
│   └── Dockerfile
├── backend/                  # Spring Boot后端应用
│   ├── src/main/java/
│   │   └── com/review/platform/
│   │       ├── controller/  # 控制器
│   │       ├── service/     # 业务逻辑
│   │       ├── repository/  # 数据访问
│   │       ├── entity/      # 实体类
│   │       ├── dto/         # 数据传输对象
│   │       ├── config/      # 配置类
│   │       └── security/    # 安全配置
│   ├── pom.xml
│   └── Dockerfile
├── docker/                   # Docker配置文件
│   ├── mysql/
│   └── nginx/
├── docs/                     # 项目文档
├── scripts/                  # 部署脚本
├── docker-compose.yml        # Docker编排文件
└── README.md                 # 项目说明
```

## 数据库设计

### 核心表结构
- **users** - 用户表
- **businesses** - 商家表
- **reviews** - 点评表
- **comments** - 评论表
- **favorites** - 收藏表
- **likes** - 点赞表

### 索引优化
- 用户名和邮箱唯一索引
- 商家名称和分类索引
- 地理位置索引
- 评分和时间索引

## API设计

### 认证接口
- `POST /api/auth/register` - 用户注册
- `POST /api/auth/login` - 用户登录
- `GET /api/auth/me` - 获取当前用户信息

### 商家接口
- `GET /api/businesses` - 获取商家列表
- `GET /api/businesses/{id}` - 获取商家详情
- `GET /api/businesses/search` - 搜索商家

### 点评接口
- `GET /api/reviews` - 获取点评列表
- `POST /api/reviews` - 发布点评
- `PUT /api/reviews/{id}` - 更新点评
- `DELETE /api/reviews/{id}` - 删除点评

## 部署方案

### 开发环境
- 使用Docker Compose本地部署
- 所有服务运行在本地容器中
- 便于开发和调试

### 生产环境
- 云服务器部署
- 域名和SSL证书配置
- 负载均衡和高可用

## 性能优化

### 前端优化
- 代码分割和懒加载
- 图片压缩和CDN
- 缓存策略优化

### 后端优化
- Redis缓存热点数据
- Elasticsearch全文搜索
- 数据库查询优化

### 数据库优化
- 索引优化
- 分页查询
- 读写分离

## 安全措施

### 认证安全
- JWT令牌认证
- 密码加密存储
- 会话管理

### 数据安全
- SQL注入防护
- XSS攻击防护
- CSRF防护

### 接口安全
- 接口限流
- 参数验证
- 权限控制

## 监控和日志

### 应用监控
- Spring Boot Actuator
- 健康检查端点
- 性能指标监控

### 日志管理
- 结构化日志
- 日志级别配置
- 日志轮转

## 扩展性设计

### 微服务架构
- 服务拆分准备
- 服务间通信
- 配置中心

### 缓存策略
- 多级缓存
- 缓存更新策略
- 缓存穿透防护

### 消息队列
- 异步处理
- 削峰填谷
- 解耦服务

## 开发计划

### 第一阶段（已完成）
- ✅ 基础架构搭建
- ✅ 用户认证系统
- ✅ 基础CRUD功能
- ✅ Docker部署

### 第二阶段（计划中）
- 🔄 搜索功能完善
- 🔄 推荐系统
- 🔄 移动端适配
- 🔄 性能优化

### 第三阶段（计划中）
- 📋 微服务拆分
- 📋 大数据分析
- 📋 AI推荐算法
- 📋 国际化支持

## 贡献指南

### 开发环境搭建
1. 克隆项目
2. 安装Docker
3. 运行部署脚本
4. 开始开发

### 代码规范
- 遵循ESLint规范
- 使用Prettier格式化
- 提交前运行测试

### 提交规范
- feat: 新功能
- fix: 修复bug
- docs: 文档更新
- style: 代码格式
- refactor: 重构
- test: 测试
- chore: 构建过程或辅助工具的变动

## 许可证

本项目采用MIT许可证，详见LICENSE文件。 