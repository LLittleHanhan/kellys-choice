package com.review.platform.repository;

import com.review.platform.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    
    List<Review> findByCleaningCompanyId(Long companyId);
    
    List<Review> findByUserId(Long userId);
    
    List<Review> findByCommentContaining(String keyword);
    
    List<Review> findByServiceType(String serviceType);
    
    List<Review> findByRating(Integer rating);
    
    Page<Review> findByCleaningCompanyId(Long companyId, Pageable pageable);
    
    Page<Review> findByRating(Integer rating, Pageable pageable);
    
    Page<Review> findByServiceType(String serviceType, Pageable pageable);
} 