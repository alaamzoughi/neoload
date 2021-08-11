package com.alaa.auth2.dto;


import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Builder
@Data
public class OperationDto {

    private Integer id;
    @NotBlank(message = "nom document is required")
    private String nomDocument ;

    @NotBlank(message = "Nom client is required")
    private String nomClient ;

    @NotBlank(message = "logo client  is required")

    private String logoClient ;
    @NotBlank(message = "date de Test is required")

    private Date dateTest ;
    @NotBlank(message = "date de Test is required")

    @NotBlank(message = "document original is required")
    private String documentOriginal ;

    private String documentModifié ;

    private UtilisateurDto utilisateur ;

}
