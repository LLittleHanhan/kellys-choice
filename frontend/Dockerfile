FROM node:18-alpine

WORKDIR /app

# 复制package.json和package-lock.json
COPY package*.json ./

# 安装依赖
RUN npm install

# 复制源代码
COPY . .

# 调试：列出文件
RUN ls -la src/

# 构建应用
RUN npm run build

# 安装serve来运行静态文件
RUN npm install -g serve

# 暴露端口
EXPOSE 3000

# 启动应用
CMD ["serve", "-s", "build", "-l", "3000"] 