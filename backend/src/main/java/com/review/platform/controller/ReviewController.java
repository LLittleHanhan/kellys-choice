package com.review.platform.controller;

import com.review.platform.dto.ReviewRequest;
import com.review.platform.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<Page<?>> getAllReviews(
            @RequestParam(required = false) Long companyId,
            @RequestParam(required = false) Integer rating,
            @RequestParam(required = false) String serviceType,
            Pageable pageable) {
        return ResponseEntity.ok(reviewService.getAllReviews(companyId, rating, serviceType, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReviewById(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.getReviewById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createReview(@Valid @RequestBody ReviewRequest request) {
        return ResponseEntity.ok(reviewService.createReview(request));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> updateReview(
            @PathVariable Long id,
            @Valid @RequestBody ReviewRequest request) {
        return ResponseEntity.ok(reviewService.updateReview(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/like")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> likeReview(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.likeReview(id));
    }

    @DeleteMapping("/{id}/like")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> unlikeReview(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.unlikeReview(id));
    }

    @GetMapping("/service-types")
    public ResponseEntity<?> getServiceTypes() {
        return ResponseEntity.ok(reviewService.getServiceTypes());
    }
} 