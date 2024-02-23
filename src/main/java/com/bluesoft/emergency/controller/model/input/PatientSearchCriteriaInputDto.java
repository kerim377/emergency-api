package com.bluesoft.emergency.controller.model.input;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class PatientSearchCriteriaInputDto {

  @Min(0)
  @Schema(description = "Page", example = "0", defaultValue = "0")
  private Integer page = 0;

  @Max(1000)
  @Min(0)
  @Value("${resource.defaultPageSize}")
  @Schema(description = "Taille par page", example = "20", defaultValue = "50")
  private Integer size;

  @Pattern(regexp = "firstName|lastName|birthDate|creationDate|reasonOfVisit", flags = Pattern.Flag.CASE_INSENSITIVE)
  @Schema(description = "Filtre, doit correspondre à firstName,lastName,birthDate,creationDate ou reasonOfVisit", example = "firstName")
  private String sortField;
  @Pattern(regexp = "asc|desc", flags = Pattern.Flag.CASE_INSENSITIVE)
  @Schema(description = "Direction: ASC ou DESC", example = "asc")
  private String sortDirection;

  @Schema(description = "Chaîne que contient le nom ou le prénom", example = "Pa")
  private String name;

  @Schema(description = "Numéro de sécurité sociale, 13 caractères", example = "1234567891234")
  private String socialSecurityNumber;

  @Schema(description = "Prénom", example = "patrick")
  private String firstName;
  @Schema(description = "Nom", example = "hardy")
  private String lastName;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  @Schema(description = "Date de naissance", example = "02/02/2024")
  private LocalDate birthDate;
}
