package com.alaa.auth2.controller;

import com.alaa.auth2.controller.api.OperationApi;
import com.alaa.auth2.dto.OperationDto;
import com.alaa.auth2.model.Operation;
import com.alaa.auth2.service.OperationService;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Slf4j


@RequiredArgsConstructor
@RestController
public class OperationController implements OperationApi {
    @Autowired
    private OperationService operationService ;

    @Override
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


    @Override
    public ResponseEntity<List<OperationDto>> getHistoriquePersonnel() {

        return ResponseEntity.ok().body(operationService.getOperationsPersonnel());

    }


    @Override
    public ResponseEntity<List<OperationDto>> getHistoriqueByUtilisateur(String username) {

        return ResponseEntity.ok().body(operationService.getOperationsByUser(username));

    }

    @Override
    public ResponseEntity<List<OperationDto>> getHistoriquePersonnelByClient(String client) {

        return ResponseEntity.ok().body(operationService.getHistoriquePersonnelByClient(client));


    }
    @Override

    public ResponseEntity<List<OperationDto>> getHistoriqueByClient(String client) {

        return ResponseEntity.ok().body(operationService.getHistoriqueByClient(client));
    }
    @Override

    public ResponseEntity<List<OperationDto>> getHistoriquePersonnelByDate(Date date) {

        return ResponseEntity.ok().body(operationService.getHistoriquePersonnelByDate(date));

    }
    @Override

    public ResponseEntity<List<OperationDto>> getHistoriqueByDate(Date date) {

        return ResponseEntity.ok().body(operationService.getHistoriqueByDate(date));
    }


    }



