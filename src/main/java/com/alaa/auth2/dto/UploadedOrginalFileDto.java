package com.alaa.auth2.dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UploadedOrginalFileDto {

    private Integer id ;

    private String fileName ;

    private String fileDownloadUri ;

    private String fileType ;

    private Long size ;

    private OperationDto operationDto ;
}
