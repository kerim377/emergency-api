package com.bluesoft.emergency.service;

import com.bluesoft.emergency.controller.model.input.PatientInputDto;
import com.bluesoft.emergency.controller.model.input.PatientSearchCriteriaInputDto;
import com.bluesoft.emergency.controller.model.output.PatientDto;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface PatientService {

  PatientDto get(UUID code);
  Page<PatientDto> find(PatientSearchCriteriaInputDto patientSearchCriteriaInputDto);
  PatientDto add(PatientInputDto patientInputDto);
  PatientDto update(UUID code, PatientInputDto patientInputDto);
  void delete(UUID code);
}
