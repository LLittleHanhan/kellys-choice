package com.review.platform.repository;

import com.review.platform.entity.CleaningCompany;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CleaningCompanyRepository extends JpaRepository<CleaningCompany, Long> {
    
    List<CleaningCompany> findByNameContainingOrAddressContaining(String name, String address);
    
    List<CleaningCompany> findByServiceArea(String serviceArea);
    
    Page<CleaningCompany> findByNameContainingOrAddressContaining(String name, String address, Pageable pageable);
    
    Page<CleaningCompany> findByServiceAreaContaining(String serviceArea, Pageable pageable);
    
    Page<CleaningCompany> findByServiceTypesContaining(String serviceType, Pageable pageable);
} 