package com.review.platform.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Review {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cleaning_company_id", nullable = false)
    private CleaningCompany cleaningCompany;
    
    @Column(nullable = false)
    private Integer rating; // 1-5星评分
    
    @Column(length = 200)
    private String title;
    
    @Column(columnDefinition = "TEXT")
    private String content;
    
    @Column(columnDefinition = "JSON")
    private String images; // JSON格式存储图片URL数组
    
    @Column(name = "service_type", length = 50)
    private String serviceType; // 服务类型：日常保洁/深度清洁/开荒保洁等
    
    @Column(name = "service_date")
    private LocalDate serviceDate; // 服务日期
    
    @Column(precision = 10, scale = 2)
    private BigDecimal price; // 服务价格
    
    @Column(name = "likes_count", nullable = false)
    private Integer likesCount = 0;
    
    @Column(nullable = false)
    private Integer status = 1; // 1:正常 0:隐藏
    
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
} 