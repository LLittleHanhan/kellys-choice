package com.review.platform.controller;

import com.review.platform.service.CleaningCompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CleaningCompanyController {

    private final CleaningCompanyService cleaningCompanyService;

    @GetMapping
    public ResponseEntity<Page<?>> getAllCompanies(
            @RequestParam(required = false) String serviceArea,
            @RequestParam(required = false) String keyword,
            Pageable pageable) {
        return ResponseEntity.ok(cleaningCompanyService.getAllCompanies(serviceArea, keyword, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCompanyById(@PathVariable Long id) {
        return ResponseEntity.ok(cleaningCompanyService.getCompanyById(id));
    }

    @GetMapping("/{id}/reviews")
    public ResponseEntity<Page<?>> getCompanyReviews(
            @PathVariable Long id,
            Pageable pageable) {
        return ResponseEntity.ok(cleaningCompanyService.getCompanyReviews(id, pageable));
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchCompanies(
            @RequestParam String q,
            @RequestParam(required = false) String serviceType,
            @RequestParam(required = false) String serviceArea,
            Pageable pageable) {
        return ResponseEntity.ok(cleaningCompanyService.searchCompanies(q, serviceType, serviceArea, pageable));
    }

    @GetMapping("/service-types")
    public ResponseEntity<?> getServiceTypes() {
        return ResponseEntity.ok(cleaningCompanyService.getServiceTypes());
    }

    @GetMapping("/service-areas")
    public ResponseEntity<?> getServiceAreas() {
        return ResponseEntity.ok(cleaningCompanyService.getServiceAreas());
    }
} 