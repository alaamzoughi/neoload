package com.alaa.auth2.dto;


import com.alaa.auth2.model.TransformedFile;
import com.alaa.auth2.model.UploadedOrginalFile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor

public class OperationDto {

    private Integer id;

    private String nomDocument ;


    private String nomClient ;

    private String nomProjet ;


    private Date dateTest ;


    private TransformedFile transformedFile ;

    private UploadedOrginalFile uploadedOrginalFile;

    private UserDto user ;


}
