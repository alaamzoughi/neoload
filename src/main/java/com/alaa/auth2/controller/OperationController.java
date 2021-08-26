package com.alaa.auth2.controller;

import com.alaa.auth2.controller.api.OperationApi;
import com.alaa.auth2.dto.OperationDto;
import com.alaa.auth2.model.Operation;
import com.alaa.auth2.service.OperationService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Slf4j

@Api
@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class OperationController implements OperationApi {
    @Autowired
    private OperationService operationService ;

    @GetMapping("/historique")
    public ResponseEntity<List<OperationDto>> getOperations() {

        ArrayList<Operation> operations = (ArrayList<Operation>) operationService.getOperations();
        ModelMapper modelMapper = new ModelMapper() ;
        ArrayList<OperationDto> list_dto = new ArrayList<OperationDto>() ;
        for (Operation o: operations ) {
            OperationDto operationDto = null ;
            operationDto= modelMapper.map(o, OperationDto.class);
            list_dto.add(operationDto) ;
        }
        return ResponseEntity.ok().body(list_dto);
    }


    @GetMapping("/historique/perso")
    public List<OperationDto> getHistoriquePersonnel() {
        return operationService.getOperationsPersonnel() ;

    }


    @GetMapping("/historique/{username}")
    public List<OperationDto> getHistoriqueByUtilisateur(@PathVariable String username) {

        return operationService.getOperationsByUser(username) ;
    }

    @GetMapping("/historique/perso/{client}")
    public List<OperationDto> getHistoriquePersonnelByClient(@PathVariable  String client) {
        return operationService.getHistoriquePersonnelByClient(client) ;

    }

    @GetMapping("/historique/client/{client}")
    public List<OperationDto> getHistoriqueByClient(@PathVariable  String client) {
        return operationService.getHistoriqueByClient(client) ;
    }

    @GetMapping("/historique/perso/date/{date}")
    public List<OperationDto> getHistoriquePersonnelByDate(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return operationService.getHistoriquePersonnelByDate(date) ;

    }

    @GetMapping("/historique/date/{date}")
    public List<OperationDto> getHistoriqueByDate(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return operationService.getHistoriqueByDate(date) ;
    }





    }



