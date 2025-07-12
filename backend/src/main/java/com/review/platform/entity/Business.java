package com.review.platform.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "cleaning_companies")
@Data
@EntityListeners(AuditingEntityListener.class)
public class CleaningCompany {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String name;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Column(length = 255)
    private String address;
    
    @Column(length = 20)
    private String phone;
    
    @Column(name = "service_area", length = 100)
    private String serviceArea;
    
    @Column(name = "business_license", length = 50)
    private String businessLicense;
    
    @Column(name = "service_types", columnDefinition = "JSON")
    private String serviceTypes; // JSON格式存储服务类型数组
    
    @Column(name = "working_hours", length = 100)
    private String workingHours;
    
    @Column(name = "avg_rating", precision = 3, scale = 2, nullable = false)
    private BigDecimal avgRating = BigDecimal.ZERO;
    
    @Column(name = "review_count", nullable = false)
    private Integer reviewCount = 0;
    
    @Column(nullable = false)
    private Integer status = 1; // 1:正常 0:禁用
    
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
} 