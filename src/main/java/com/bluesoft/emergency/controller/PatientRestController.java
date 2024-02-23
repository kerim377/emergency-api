package com.bluesoft.emergency.controller;

import com.bluesoft.emergency.controller.model.input.PatientInputDto;
import com.bluesoft.emergency.controller.model.input.PatientSearchCriteriaInputDto;
import com.bluesoft.emergency.controller.model.output.PatientDto;
import com.bluesoft.emergency.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("patients")
@Tag(name = "API de Gestion des Malades aux Urgences Hospitalières")
@AllArgsConstructor
public class PatientRestController {

  final PatientService patientService;

  @PostMapping
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Malade ajouté avec succès"),
      @ApiResponse(responseCode = "400", description = "Le Malade ne respecte pas le format"),
  })
  @Operation(summary = "Enregistrement d'un malade", description = "Returne le malade enregistré")
  PatientDto add(@RequestBody @Valid PatientInputDto patientInputDto) {
    return patientService.add(patientInputDto);
  }

  @GetMapping
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "La recherche s'est bien passée"),
      @ApiResponse(responseCode = "400", description = "L'objet de recherche ne respecte pas le format"),
  })
  @Operation(summary = "Liste des malades aux urgences", description = "Returne la liste des malades enregistrés ainsi que leur nombre")
  public Page<PatientDto> find(@Valid PatientSearchCriteriaInputDto patientSearchCriteriaInputDto) {
    return patientService.find(patientSearchCriteriaInputDto);
  }

  @Operation(summary = "Consultation d'un dossier médical", description = "Returne le malade par identifiant")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Malade trouvé"),
      @ApiResponse(responseCode = "404", description = "Malade non trouvé")
  })
  @GetMapping("/{code}")
  PatientDto get(@PathVariable UUID code) {
    return patientService.get(code);
  }


  @Operation(summary = "Mise à jour des informations médicales", description = "Returne le malade mis à jour")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Mise à jours avec succès"),
      @ApiResponse(responseCode = "400", description = "Le Malade ne respecte pas le format"),
      @ApiResponse(responseCode = "404", description = "Malade non trouvé")
  })
  @PutMapping("/{code}")
  PatientDto update(@PathVariable UUID code, @RequestBody @Valid PatientInputDto patientInputDto) {
    return patientService.update(code, patientInputDto);
  }

  @Operation(summary = "Libération d'un malade", description = "Supprime le malade de la liste des malades aux urgences")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Suppression avec succès"),
      @ApiResponse(responseCode = "404", description = "Malade non trouvé")
  })
  @DeleteMapping("/{code}")
  void delete(@PathVariable UUID code) {
    patientService.delete(code);
  }


}
