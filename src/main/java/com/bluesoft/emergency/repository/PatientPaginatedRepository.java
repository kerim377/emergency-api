package com.bluesoft.emergency.repository;

import com.bluesoft.emergency.repository.infrastructure.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PatientPaginatedRepository extends PagingAndSortingRepository<Patient, Long> {

  Page<Patient> findAll(Specification<Patient> spec, Pageable pageable);
}
