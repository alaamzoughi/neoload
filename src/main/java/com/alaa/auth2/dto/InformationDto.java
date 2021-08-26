package com.alaa.auth2.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InformationDto {

    @NotBlank(message = "nom_projet are required")
    private String nom_projet ;
    @NotBlank(message = "date du test are required")
    private Date date_test ;
    @NotNull(message = "nombre de consultant are required")
    private Integer nombre_consultants ;
    @NotNull(message = "Consultant are required")
    private ArrayList<ConsultantDto> consultants ;
    @NotBlank(message = "nom_client are required")
    private String nom_client ;
    @NotNull(message = "logo client are required")
    private String logo_client ;
    private boolean statut_de_test ;
    private boolean statut_de_application ;
    private boolean poursuite_des_tests ;



}
