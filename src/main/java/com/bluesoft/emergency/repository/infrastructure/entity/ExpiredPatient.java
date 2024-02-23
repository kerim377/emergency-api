package com.bluesoft.emergency.repository.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExpiredPatient {

  @Id
  @Column(unique = true, name = "expiry_patient_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long expiryPatientId;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "birth_date")
  private LocalDate birthDate;

  @Column(name = "social_security_number")
  private String socialSecurityNumber;

  @Column(name = "reason_of_visit")
  private String reasonOfVisit;

  @Column(unique = true, name = "patient_code")
  private UUID patientCode;

  @Column(unique = true, name = "patient_id")
  private Long patientId;

  @Column(name = "creation_date")
  private LocalDateTime creationDate;

  @Column(name = "expiry_date", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT now()")
  private LocalDateTime expiryDate;
}
