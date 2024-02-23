package com.bluesoft.emergency.repository;

import com.bluesoft.emergency.repository.infrastructure.entity.Patient;
import org.springframework.data.repository.Repository;

import java.util.Optional;
import java.util.UUID;

public interface PatientRepository extends Repository<Patient, Long> {
  Patient save(Patient patient);
  Patient findByPatientId(Long code);
  Optional<Patient> findByPatientCode(UUID code);
  void deleteByPatientId(Long id);
}
