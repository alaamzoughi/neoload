package com.alaa.auth2.handlers;

import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDto {

    private Integer httpCode;

    private String message;

    private Map<String, String> validationErrors= new HashMap<>();  ;
}
