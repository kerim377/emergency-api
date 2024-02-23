package com.bluesoft.emergency.repository;

import com.bluesoft.emergency.repository.infrastructure.entity.ExpiredPatient;
import org.springframework.data.repository.Repository;

public interface ExpiredPatientRepository extends Repository<ExpiredPatient, Long> {

  ExpiredPatient save(ExpiredPatient patient);
}
