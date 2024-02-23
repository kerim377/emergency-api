package com.bluesoft.emergency.repository.infrastructure;

import com.bluesoft.emergency.repository.infrastructure.entity.Patient;
import com.bluesoft.emergency.controller.model.input.PatientSearchCriteriaInputDto;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

@Getter
@AllArgsConstructor
public class PatientSpecification implements Specification<Patient> {

  private PatientSearchCriteriaInputDto patientSearchCriteriaInputDto;

  @Override
  public Predicate toPredicate(Root<Patient> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
    Predicate predicate = criteriaBuilder.conjunction();

    if (patientSearchCriteriaInputDto.getFirstName() != null) {
      predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("firstName"), patientSearchCriteriaInputDto.getFirstName()));
    }
    if (patientSearchCriteriaInputDto.getLastName() != null) {
      predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("lastName"), patientSearchCriteriaInputDto.getFirstName()));
    }
    if (patientSearchCriteriaInputDto.getSocialSecurityNumber() != null) {
      predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("socialSecurityNumber"), patientSearchCriteriaInputDto.getSocialSecurityNumber()));
    }
    if (patientSearchCriteriaInputDto.getName() != null) {
      predicate = criteriaBuilder.and(predicate, criteriaBuilder.or(criteriaBuilder.like(root.get("firstName"), "%" + patientSearchCriteriaInputDto.getName() + "%"), criteriaBuilder.like(root.get("lastName"), "%" + patientSearchCriteriaInputDto.getName() + "%")));
    }
    if (patientSearchCriteriaInputDto.getBirthDate() != null) {
      predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("birthDate"), patientSearchCriteriaInputDto.getBirthDate()));
    }
    return predicate;
  }
}
