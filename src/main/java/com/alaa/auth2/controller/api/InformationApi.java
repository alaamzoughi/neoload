package com.alaa.auth2.controller.api;

import com.alaa.auth2.dto.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Api
@RequestMapping("/api")
public interface InformationApi


{
    @PostMapping(value = "/generate/doc")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "sucess"),
    })
    public ResponseDto generateDoc(
            @Nullable @RequestParam(value = "file",required = false) MultipartFile file,
            @Nullable @RequestParam(value = "image",required = false) MultipartFile image,
            @RequestParam(value = "input " ,  required = false , defaultValue = "{\n" +
                    "  \"consultants\": [\n" +
                    "    {\n" +
                    "      \"email_RATP\": \"string\",\n" +
                    "      \"ligne_directe_RATP\": 0,\n" +
                    "      \"nom\": \"string\",\n" +
                    "      \"poste\": \"string\"\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"date_test\": \"2021-08-24\",\n" +
                    "  \"logo_client\": \"string\",\n" +
                    "  \"nom_client\": \"string\",\n" +
                    "  \"nom_projet\": \"string\",\n" +
                    "  \"nombre_consultants\": 0,\n" +
                    "  \"poursuite_des_tests\": false,\n" +
                    "  \"statut_de_application\": false,\n" +
                    "  \"statut_de_test\": true\n" +
                    "}") String s ) ;

}
