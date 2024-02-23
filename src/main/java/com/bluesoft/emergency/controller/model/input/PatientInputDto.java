package com.bluesoft.emergency.controller.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PatientInputDto {
  @NotNull(message = "first name is a required field")
  @Size(min = 2, message = "first name is a too short")
  @Size(max = 1000, message = "first name is a too long")
  @Schema(description ="Prénom du patient, entre 2 et 1000 caractères", example = "Patrick")
  private String firstName;

  @NotNull(message = "last name is a required field")
  @Size(min = 2, message = "last name is a too short")
  @Size(max = 1000, message = "last name is a too long")
  @Schema(description ="Nom du patient, entre 2 et 1000 caractères", example = "Hardy")
  private String lastName;

  @Schema(description ="Date de naissance", example = "2024-02-02")
  private LocalDate birthDate;
  @NotNull
  @Pattern(regexp = "^\\d{13}$", message = "socialSecurityNumber should have 13 digits")
  @Schema(description ="Numéro de sécurité social, 13 caractères", example = "1234567891234")
  private String socialSecurityNumber;
  @NotNull
  @Schema(description ="Motif de consultation", example = "douleurs intense")
  private String reasonOfVisit;
}
