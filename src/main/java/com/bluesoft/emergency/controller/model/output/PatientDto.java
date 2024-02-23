package com.bluesoft.emergency.controller.model.output;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class PatientDto {
  @Schema(description ="Identifiant du malade", example = "92e26ebf-e7b4-47d0-9bfa-6a636dbef344")
  private UUID code;
  @Schema(description ="Prénom du malade", example = "Patrick")
  private String firstName;
  @Schema(description ="Nom du malade", example = "Hardy")
  private String lastName;
  @Schema(description ="Date de naissance du malade", example = "Hardy")
  private LocalDate birthDate;
  @Schema(description ="Numéro de sécurité sociale du malade", example = "1234567891234")
  private String socialSecurityNumber;
  @Schema(description ="Motif de consultation", example = "Douleurs")
  private String reasonOfVisit;
  @Schema(description ="Date de création du malade")
  private LocalDateTime creationDate;
}
