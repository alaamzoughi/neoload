package com.alaa.auth2.controller.api;


import com.alaa.auth2.dto.OperationDto;
import com.alaa.auth2.model.Operation;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Api
@RequestMapping("/api")
public interface OperationApi {
    @GetMapping("/historique")
    public ResponseEntity<List<OperationDto>> getOperations() ;

    @GetMapping("/historique/perso")
    public ResponseEntity<List<OperationDto>> getHistoriquePersonnel() ;

    @GetMapping("/historique/{username}")
    public ResponseEntity<List<OperationDto>> getHistoriqueByUtilisateur(@PathVariable String username) ;

    @GetMapping("/historique/perso/{client}")
    public ResponseEntity<List<OperationDto>> getHistoriquePersonnelByClient(@PathVariable  String client) ;

    @GetMapping("/historique/client/{client}")
    public ResponseEntity<List<OperationDto>> getHistoriqueByClient(@PathVariable  String client) ;

    @GetMapping("/historique/perso/date/{date}")
    public ResponseEntity<List<OperationDto>> getHistoriquePersonnelByDate(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date);

    @GetMapping("/historique/date/{date}")
    public ResponseEntity<List<OperationDto>> getHistoriqueByDate(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) ;
}
