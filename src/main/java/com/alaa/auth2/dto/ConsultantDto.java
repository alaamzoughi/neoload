package com.alaa.auth2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsultantDto {
    @NotBlank(message = "nom are required")
    private String nom ;
    @NotBlank(message = "poste are required")
    private String poste ;
    @NotBlank(message = "Email_RATP are required")
    private String Email_RATP ;
    @NotNull(message = "Ligne_directe_RATP are required")
    private Long Ligne_directe_RATP ;


}
