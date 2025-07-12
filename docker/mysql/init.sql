-- 创建数据库
CREATE DATABASE IF NOT EXISTS review_platform CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE review_platform;

-- 用户表
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    nickname VARCHAR(50),
    avatar VARCHAR(255),
    phone VARCHAR(20),
    status TINYINT DEFAULT 1 COMMENT '1:正常 0:禁用',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_username (username),
    INDEX idx_email (email)
);

-- 清洁公司表
CREATE TABLE cleaning_companies (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    address VARCHAR(255),
    phone VARCHAR(20),
    service_area VARCHAR(100) COMMENT '服务区域',
    business_license VARCHAR(50) COMMENT '营业执照号',
    service_types JSON COMMENT '服务类型JSON数组',
    working_hours VARCHAR(100) COMMENT '营业时间',
    avg_rating DECIMAL(3, 2) DEFAULT 0.00,
    review_count INT DEFAULT 0,
    status TINYINT DEFAULT 1 COMMENT '1:正常 0:禁用',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_name (name),
    INDEX idx_service_area (service_area),
    INDEX idx_avg_rating (avg_rating)
);

-- 点评表
CREATE TABLE reviews (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    company_id BIGINT NOT NULL,
    rating TINYINT NOT NULL COMMENT '1-5星评分',
    title VARCHAR(200),
    content TEXT,
    images JSON,
    service_type VARCHAR(50) COMMENT '服务类型：日常保洁/深度清洁/开荒保洁等',
    service_date DATE COMMENT '服务日期',
    price DECIMAL(10, 2) COMMENT '服务价格',
    likes_count INT DEFAULT 0,
    status TINYINT DEFAULT 1 COMMENT '1:正常 0:隐藏',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (company_id) REFERENCES cleaning_companies(id),
    INDEX idx_user_id (user_id),
    INDEX idx_company_id (company_id),
    INDEX idx_rating (rating),
    INDEX idx_service_type (service_type),
    INDEX idx_created_at (created_at)
);

-- 评论表
CREATE TABLE comments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    review_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    content TEXT NOT NULL,
    parent_id BIGINT DEFAULT NULL,
    likes_count INT DEFAULT 0,
    status TINYINT DEFAULT 1 COMMENT '1:正常 0:隐藏',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (review_id) REFERENCES reviews(id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (parent_id) REFERENCES comments(id),
    INDEX idx_review_id (review_id),
    INDEX idx_user_id (user_id)
);

-- 收藏表
CREATE TABLE favorites (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    company_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (company_id) REFERENCES cleaning_companies(id),
    UNIQUE KEY unique_user_company (user_id, company_id),
    INDEX idx_user_id (user_id),
    INDEX idx_company_id (company_id)
);

-- 点赞表
CREATE TABLE likes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    target_id BIGINT NOT NULL,
    target_type ENUM('review', 'comment') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id),
    UNIQUE KEY unique_user_target (user_id, target_id, target_type),
    INDEX idx_user_id (user_id),
    INDEX idx_target (target_id, target_type)
);

-- 插入测试数据
INSERT INTO users (username, email, password, nickname) VALUES
('admin', 'admin@example.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', '管理员'),
('user1', 'user1@example.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', '用户1'),
('user2', 'user2@example.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', '用户2');

INSERT INTO cleaning_companies (name, description, address, phone, service_area, business_license, service_types, working_hours) VALUES
('洁净家园清洁服务', '专业提供家庭清洁、办公室清洁、开荒保洁等服务，员工经过专业培训，服务态度好，清洁效果佳。', '北京市朝阳区建国路88号', '010-88888888', '朝阳区、海淀区、东城区', 'BJ2024001', '["日常保洁", "深度清洁", "开荒保洁", "玻璃清洗"]', '周一至周日 8:00-18:00'),
('绿净清洁公司', '专注于环保清洁服务，使用环保清洁剂，为您的家庭和办公环境提供健康清洁服务。', '北京市海淀区中关村大街1号', '010-66666666', '海淀区、西城区、丰台区', 'BJ2024002', '["日常保洁", "深度清洁", "地毯清洗", "空调清洗"]', '周一至周六 7:00-19:00'),
('明亮清洁服务', '专业团队提供全方位清洁服务，包括家庭清洁、商业清洁、工业清洁等，价格实惠，质量保证。', '北京市东城区王府井大街100号', '010-77777777', '东城区、西城区、朝阳区', 'BJ2024003', '["日常保洁", "开荒保洁", "外墙清洗", "地板打蜡"]', '周一至周日 6:00-20:00');

INSERT INTO reviews (user_id, company_id, rating, title, content, service_type, service_date, price) VALUES
(2, 1, 5, '服务很专业', '洁净家园的服务真的很专业，阿姨们工作认真负责，清洁效果很好，家里焕然一新！', '日常保洁', '2024-01-15', 200.00),
(3, 1, 4, '价格合理', '服务态度不错，清洁效果也很好，价格比较合理，会继续选择这家公司。', '深度清洁', '2024-01-14', 350.00),
(2, 2, 5, '环保清洁很棒', '绿净清洁使用环保清洁剂，清洁效果很好，而且没有刺鼻的味道，很满意！', '日常保洁', '2024-01-13', 180.00),
(3, 3, 4, '开荒保洁专业', '明亮清洁的开荒保洁服务很专业，新房子清洁得很干净，值得推荐。', '开荒保洁', '2024-01-12', 500.00); 