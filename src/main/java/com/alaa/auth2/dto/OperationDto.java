package com.alaa.auth2.dto;


import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor

public class OperationDto {

    private Integer id;
    @NotBlank(message = "nom document is required")
    private String nomDocument ;

    @NotBlank(message = "Nom client is required")
    private String nomClient ;

    @NotBlank(message = "Nom projet is required")
    private String nomProjet ;

    @NotBlank(message = "logo client  is required")

    private String logoClient ;
    @NotBlank(message = "date de Test is required")

    private String dateTest ;
    @NotBlank(message = "date de Test is required")

    @NotBlank(message = "document original is required")
    private String documentOriginal ;

    private String documentModifié ;

    private UserDto user ;


}
