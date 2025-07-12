package com.review.platform.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class ReviewRequest {
    
    @NotNull(message = "清洁公司ID不能为空")
    private Long companyId;
    
    @NotNull(message = "评分不能为空")
    @Min(value = 1, message = "评分最小为1")
    @Max(value = 5, message = "评分最大为5")
    private Integer rating;
    
    @Size(max = 200, message = "标题长度不能超过200个字符")
    private String title;
    
    @NotBlank(message = "点评内容不能为空")
    @Size(max = 2000, message = "点评内容长度不能超过2000个字符")
    private String content;
    
    private List<String> images;
    
    @NotBlank(message = "服务类型不能为空")
    @Size(max = 50, message = "服务类型长度不能超过50个字符")
    private String serviceType; // 日常保洁/深度清洁/开荒保洁等
    
    @NotNull(message = "服务日期不能为空")
    private LocalDate serviceDate;
    
    @DecimalMin(value = "0.0", message = "价格不能为负数")
    @DecimalMax(value = "99999.99", message = "价格不能超过99999.99")
    private BigDecimal price;
} 