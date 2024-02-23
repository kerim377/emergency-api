package com.bluesoft.emergency.repository.infrastructure;

import com.bluesoft.emergency.controller.model.input.PatientSearchCriteriaInputDto;
import com.bluesoft.emergency.repository.infrastructure.entity.Patient;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PatientSpecificationUTest {

  @Mock
  private PatientSearchCriteriaInputDto patientSearchCriteriaInputDto;
  @InjectMocks
  private PatientSpecification patientSpecification;

  private CriteriaBuilder criteriaBuilderMock;
  private CriteriaQuery criteriaQueryMock;
  private Root<Patient> personRootMock;

  @BeforeEach
  public void setUp() {
  }

  @Test
  void toPredicate() {

  }
}