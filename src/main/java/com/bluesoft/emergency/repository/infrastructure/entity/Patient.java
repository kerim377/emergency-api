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
public class Patient {

  @Id
  @Column(unique = true, name = "patient_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long patientId;

  @Column(unique = true, name = "patient_code")
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID patientCode;

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

  @Column(name = "creation_date", updatable = false)
  private LocalDateTime creationDate;

  @PrePersist
  protected void onCreate() {
    setPatientCode(java.util.UUID.randomUUID());
    setCreationDate(LocalDateTime.now());
  }
}
