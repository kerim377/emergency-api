package com.bluesoft.emergency.configuration;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {

  @Schema(description ="HTTP status code de l'erreur", example = "400")
  private int exceptionCode;
  @Schema(description ="HTTP status name de l'erreur", example = "BAD_REQUEST")
  private String exceptionMessage;
  @Schema(description ="Détail de l'erreur", example = "X Input is required")
  private String descriptionMessage;

}
