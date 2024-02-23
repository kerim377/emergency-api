package com.bluesoft.emergency.repository.infrastructure;

import com.bluesoft.emergency.repository.infrastructure.entity.ExpiredPatient;
import com.bluesoft.emergency.repository.infrastructure.entity.Patient;
import com.bluesoft.emergency.controller.model.input.PatientInputDto;
import com.bluesoft.emergency.controller.model.output.PatientDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PatientMapper {

  @Mappings({
      @Mapping(target = "code", source = "patientCode"),
  })
  PatientDto toDto(Patient patient);

  @Mappings({})
  Patient fromDto(PatientInputDto patientInputDto);

  @Mappings({})
  ExpiredPatient toExpiryPatient(Patient patient);


}
