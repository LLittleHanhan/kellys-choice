package com.review.platform.service;

import com.review.platform.dto.ReviewRequest;
import com.review.platform.entity.Review;
import com.review.platform.entity.CleaningCompany;
import com.review.platform.entity.User;
import com.review.platform.repository.ReviewRepository;
import com.review.platform.repository.CleaningCompanyRepository;
import com.review.platform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    
    @Autowired
    private ReviewRepository reviewRepository;
    
    @Autowired
    private CleaningCompanyRepository cleaningCompanyRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    public Page<Review> getAllReviews(Long companyId, Integer rating, String serviceType, Pageable pageable) {
        if (companyId != null) {
            return reviewRepository.findByCleaningCompanyId(companyId, pageable);
        }
        if (rating != null) {
            return reviewRepository.findByRating(rating, pageable);
        }
        if (serviceType != null) {
            return reviewRepository.findByServiceType(serviceType, pageable);
        }
        return reviewRepository.findAll(pageable);
    }
    
    public Optional<Review> getReviewById(Long id) {
        return reviewRepository.findById(id);
    }
    
    public Review createReview(ReviewRequest request) {
        // 验证清洁公司是否存在
        Optional<CleaningCompany> company = cleaningCompanyRepository.findById(request.getCompanyId());
        if (!company.isPresent()) {
            throw new RuntimeException("清洁公司不存在");
        }
        
        // 验证用户是否存在（这里应该从SecurityContext获取当前用户）
        Optional<User> user = userRepository.findById(1L); // 临时使用ID 1
        if (!user.isPresent()) {
            throw new RuntimeException("用户不存在");
        }
        
        Review review = new Review();
        review.setUser(user.get());
        review.setCleaningCompany(company.get());
        review.setRating(request.getRating());
        review.setTitle(request.getTitle());
        review.setContent(request.getContent());
        review.setServiceType(request.getServiceType());
        review.setServiceDate(request.getServiceDate());
        review.setPrice(request.getPrice());
        
        return reviewRepository.save(review);
    }
    
    public Review updateReview(Long id, ReviewRequest request) {
        Optional<Review> existingReview = reviewRepository.findById(id);
        if (!existingReview.isPresent()) {
            throw new RuntimeException("评价不存在");
        }
        
        Review review = existingReview.get();
        review.setRating(request.getRating());
        review.setTitle(request.getTitle());
        review.setContent(request.getContent());
        review.setServiceType(request.getServiceType());
        review.setServiceDate(request.getServiceDate());
        review.setPrice(request.getPrice());
        
        return reviewRepository.save(review);
    }
    
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
    
    public Review likeReview(Long id) {
        Optional<Review> reviewOpt = reviewRepository.findById(id);
        if (reviewOpt.isPresent()) {
            Review review = reviewOpt.get();
            review.setLikesCount(review.getLikesCount() + 1);
            return reviewRepository.save(review);
        }
        throw new RuntimeException("评价不存在");
    }
    
    public Review unlikeReview(Long id) {
        Optional<Review> reviewOpt = reviewRepository.findById(id);
        if (reviewOpt.isPresent()) {
            Review review = reviewOpt.get();
            if (review.getLikesCount() > 0) {
                review.setLikesCount(review.getLikesCount() - 1);
                return reviewRepository.save(review);
            }
        }
        throw new RuntimeException("评价不存在");
    }
    
    public List<String> getServiceTypes() {
        return Arrays.asList("日常保洁", "深度清洁", "开荒保洁", "玻璃清洗", "地毯清洗", "沙发清洗", "地板打蜡", "除甲醛");
    }
    
    public List<Review> getReviewsByCompanyId(Long companyId) {
        return reviewRepository.findByCleaningCompanyId(companyId);
    }
    
    public List<Review> getReviewsByUserId(Long userId) {
        return reviewRepository.findByUserId(userId);
    }
    
    public List<Review> searchReviews(String keyword) {
        return reviewRepository.findByCommentContaining(keyword);
    }
    
    public List<Review> getReviewsByServiceType(String serviceType) {
        return reviewRepository.findByServiceType(serviceType);
    }
    
    public List<Review> getReviewsByRating(Integer rating) {
        return reviewRepository.findByRating(rating);
    }
} 