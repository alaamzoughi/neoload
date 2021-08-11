package com.alaa.auth2.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode()

public class UtilisateurDto  {

    private Integer id;
    @NotBlank(message = "Name is required")
    private String nom;
    @NotBlank(message = "email is required")
    @Email
    private String email;
    @NotBlank(message = "photo is required")
    private String photo;
    @NotBlank(message = "poste is required")
    private String poste ;
    @JsonIgnore
    private List<OperationDto>  operations ;
}

