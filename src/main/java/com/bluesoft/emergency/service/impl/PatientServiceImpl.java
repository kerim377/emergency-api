package com.bluesoft.emergency.service.impl;

import com.bluesoft.emergency.repository.infrastructure.PatientMapper;
import com.bluesoft.emergency.repository.infrastructure.PatientSpecification;
import com.bluesoft.emergency.repository.infrastructure.entity.ExpiredPatient;
import com.bluesoft.emergency.repository.infrastructure.entity.Patient;
import com.bluesoft.emergency.controller.model.input.PatientInputDto;
import com.bluesoft.emergency.controller.model.input.PatientSearchCriteriaInputDto;
import com.bluesoft.emergency.controller.model.output.PatientDto;
import com.bluesoft.emergency.repository.ExpiredPatientRepository;
import com.bluesoft.emergency.repository.PatientPaginatedRepository;
import com.bluesoft.emergency.repository.PatientRepository;
import com.bluesoft.emergency.service.PatientService;
import jakarta.ws.rs.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class PatientServiceImpl implements PatientService {

  private PatientRepository patientRepository;
  private PatientPaginatedRepository patientPaginatedRepository;
  private ExpiredPatientRepository expiredPatientRepository;
  private PatientMapper patientMapper;

  @Override
  public PatientDto get(UUID code) {
    return patientRepository.findByPatientCode(code).map(patient -> patientMapper.toDto(patient))
        .orElseThrow(NotFoundException::new);
  }

  @Override
  public Page<PatientDto> find(PatientSearchCriteriaInputDto patientSearchCriteriaInputDto) {
    final Pageable pageable = buildPageable(patientSearchCriteriaInputDto);
    final PatientSpecification patientSpecification = new PatientSpecification(patientSearchCriteriaInputDto);
    return patientPaginatedRepository.findAll(patientSpecification, pageable)
        .map(patient -> patientMapper.toDto(patient));
  }

  private Pageable buildPageable(PatientSearchCriteriaInputDto patientSearchCriteriaInputDto) {
    final Sort orders = buildSort(patientSearchCriteriaInputDto);
    return PageRequest.of(patientSearchCriteriaInputDto.getPage(), patientSearchCriteriaInputDto.getSize(), orders);
  }

  private Sort buildSort(PatientSearchCriteriaInputDto patientSearchCriteriaInputDto) {
    if (patientSearchCriteriaInputDto.getSortField() == null) {
      return Sort.unsorted();
    }
    return Sort.by(Sort.Direction.fromOptionalString(patientSearchCriteriaInputDto.getSortDirection())
        .orElse(Sort.Direction.ASC), patientSearchCriteriaInputDto.getSortField());
  }

  @Override
  public PatientDto add(PatientInputDto patientInputDto) {
    Patient patient = patientMapper.fromDto(patientInputDto);
    Patient savedPatient = patientRepository.save(patient);
    return patientMapper.toDto(patientRepository.findByPatientId(savedPatient.getPatientId()));
  }

  @Override
  public PatientDto update(UUID code, PatientInputDto patientInputDto) {
    Patient existingPatient = patientRepository.findByPatientCode(code).orElseThrow(NotFoundException::new);

    Patient patientToUpdate = patientMapper.fromDto(patientInputDto);
    patientToUpdate.setPatientId(existingPatient.getPatientId());
    patientToUpdate.setPatientCode(existingPatient.getPatientCode());
    patientToUpdate.setCreationDate(existingPatient.getCreationDate());

    Patient updatedPatient = patientRepository.save(patientToUpdate);
    return patientMapper.toDto(patientRepository.findByPatientId(updatedPatient.getPatientId()));
  }

  @Override
  public void delete(UUID code) {
    Patient existingPatient = patientRepository.findByPatientCode(code).orElseThrow(NotFoundException::new);
    ExpiredPatient expiredPatient = patientMapper.toExpiryPatient(existingPatient);
    expiredPatient.setExpiryDate(LocalDateTime.now());
    expiredPatientRepository.save(expiredPatient);

    patientRepository.deleteByPatientId(existingPatient.getPatientId());
  }
}
