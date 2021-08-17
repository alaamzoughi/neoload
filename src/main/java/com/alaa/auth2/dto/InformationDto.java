package com.alaa.auth2.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InformationDto {

    @NotBlank(message = "nom_projet are required")
    private String nom_projet ;
    @NotBlank(message = "date du test are required")
    private String date_test ;
    @NotNull(message = "nombre de consultant are required")
    private Integer nombre_consultants ;
    @NotNull(message = "Consultant are required")
    private ArrayList<ConsultantDto> consultants ;

    @NotNull(message = "logo client are required")
    private String logo_client ;


}
