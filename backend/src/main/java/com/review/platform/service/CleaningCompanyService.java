package com.review.platform.service;

import com.review.platform.entity.CleaningCompany;
import com.review.platform.entity.Review;
import com.review.platform.repository.CleaningCompanyRepository;
import com.review.platform.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CleaningCompanyService {
    
    @Autowired
    private CleaningCompanyRepository cleaningCompanyRepository;
    
    @Autowired
    private ReviewRepository reviewRepository;
    
    public Page<CleaningCompany> getAllCompanies(String serviceArea, String keyword, Pageable pageable) {
        if (serviceArea != null && !serviceArea.isEmpty()) {
            return cleaningCompanyRepository.findByServiceAreaContaining(serviceArea, pageable);
        }
        if (keyword != null && !keyword.isEmpty()) {
            return cleaningCompanyRepository.findByNameContainingOrAddressContaining(keyword, keyword, pageable);
        }
        return cleaningCompanyRepository.findAll(pageable);
    }
    
    public Optional<CleaningCompany> getCompanyById(Long id) {
        return cleaningCompanyRepository.findById(id);
    }
    
    public Page<Review> getCompanyReviews(Long companyId, Pageable pageable) {
        return reviewRepository.findByCleaningCompanyId(companyId, pageable);
    }
    
    public Page<?> searchCompanies(String q, String serviceType, String serviceArea, Pageable pageable) {
        if (serviceType != null && !serviceType.isEmpty()) {
            return cleaningCompanyRepository.findByServiceTypesContaining(serviceType, pageable);
        }
        if (serviceArea != null && !serviceArea.isEmpty()) {
            return cleaningCompanyRepository.findByServiceAreaContaining(serviceArea, pageable);
        }
        return cleaningCompanyRepository.findByNameContainingOrAddressContaining(q, q, pageable);
    }
    
    public List<String> getServiceTypes() {
        return Arrays.asList("日常保洁", "深度清洁", "开荒保洁", "玻璃清洗", "地毯清洗", "沙发清洗", "地板打蜡", "除甲醛");
    }
    
    public List<String> getServiceAreas() {
        return Arrays.asList("朝阳区", "海淀区", "西城区", "东城区", "丰台区", "石景山区", "通州区", "昌平区", "大兴区", "顺义区");
    }
    
    public CleaningCompany createCompany(CleaningCompany company) {
        return cleaningCompanyRepository.save(company);
    }
    
    public CleaningCompany updateCompany(Long id, CleaningCompany company) {
        if (cleaningCompanyRepository.existsById(id)) {
            company.setId(id);
            return cleaningCompanyRepository.save(company);
        }
        throw new RuntimeException("清洁公司不存在");
    }
    
    public void deleteCompany(Long id) {
        cleaningCompanyRepository.deleteById(id);
    }
    
    public List<CleaningCompany> searchCompanies(String keyword) {
        return cleaningCompanyRepository.findByNameContainingOrAddressContaining(keyword, keyword);
    }
    
    public List<CleaningCompany> getCompaniesByServiceArea(String serviceArea) {
        return cleaningCompanyRepository.findByServiceArea(serviceArea);
    }
} 