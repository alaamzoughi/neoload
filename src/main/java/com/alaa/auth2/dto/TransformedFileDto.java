package com.alaa.auth2.dto;

import lombok.*;

import javax.persistence.Column;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransformedFileDto {


    private Integer id ;

    private String fileName ;

    private String fileDownloadUri ;

    private String fileType ;

    private Long size ;

    private OperationDto operationDto ;


}
