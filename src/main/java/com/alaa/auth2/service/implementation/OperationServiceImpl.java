package com.alaa.auth2.service.implementation;


import com.alaa.auth2.dto.OperationDto;
import com.alaa.auth2.exception.EntityNotFoundException;
import com.alaa.auth2.model.Operation;
import com.alaa.auth2.model.User;
import com.alaa.auth2.repo.OperationRepository;

import com.alaa.auth2.service.OperationService;
import com.alaa.auth2.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class OperationServiceImpl implements OperationService {
    @Autowired
    private OperationRepository operationRepository ;
    @Autowired 
    private UserService userService ;

    @Override
    public Operation save(Operation operation) {
        return operationRepository.save(operation);
    }

    @Override
    public List<Operation> getOperations() {
        return operationRepository.findAll();
    }

    @Override
    public List <OperationDto> getOperationsPersonnel() {

        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        ArrayList<Operation> operations = (ArrayList<Operation>) this.getOperations();
        ArrayList<OperationDto> list_dto = new ArrayList<OperationDto>() ;
        for (Operation o:operations
             ) {
            if (o.getUser().getUsername().equals(username)) {
                ModelMapper modelMapper = new ModelMapper() ;
                OperationDto operationDto= modelMapper.map(o, OperationDto.class);
                list_dto.add(operationDto) ;
            }
        }
        return list_dto ;

    }

    @Override
    public List<OperationDto> getOperationsByUser(String username) {
        ArrayList<Operation> operations = (ArrayList<Operation>) this.getOperations();
        ArrayList<OperationDto> list_dto = new ArrayList<OperationDto>();
        for (Operation o:operations
        ) {
            if (o.getUser().getUsername().equals(username)) {

                ModelMapper modelMapper = new ModelMapper() ;
                OperationDto operationDto= modelMapper.map(o, OperationDto.class);
                list_dto.add(operationDto) ;
            }
            else {
                throw new EntityNotFoundException("user non existant ");
            }
        }
        return list_dto ;

    }

    @Override
    public List<OperationDto> getHistoriquePersonnelByClient(String client) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        ArrayList<Operation> operations = (ArrayList<Operation>) this.getOperations();
        ArrayList<OperationDto> list_dto = new ArrayList<OperationDto>() ;
        for (Operation o:operations
        ) {
            if (o.getUser().getUsername().equals(username) || o.getNomClient().equals(client)) {
                ModelMapper modelMapper = new ModelMapper() ;
                OperationDto operationDto= modelMapper.map(o, OperationDto.class);
                list_dto.add(operationDto) ;
            }
            else {
                throw new EntityNotFoundException("client non existant ");
            }
        }
        return list_dto ;
    }

    @Override
    public List<OperationDto> getHistoriqueByClient(String client) {
        ArrayList<Operation> operations = (ArrayList<Operation>) this.getOperations();
        ArrayList<OperationDto> list_dto = new ArrayList<OperationDto>() ;
        for (Operation o:operations
        ) {
            if (o.getNomClient().equals(client)) {
                ModelMapper modelMapper = new ModelMapper() ;
                OperationDto operationDto= modelMapper.map(o, OperationDto.class);
                list_dto.add(operationDto) ;
            }
            else {
                throw new EntityNotFoundException("client non existant ");
            }
        }
        return list_dto ;
    }

    @Override
    public List<OperationDto> getHistoriquePersonnelByDate(Date date) {
        ArrayList<Operation> operations = (ArrayList<Operation>) this.getOperations();
        ArrayList<OperationDto> list_dto = new ArrayList<OperationDto>() ;
        for (Operation o:operations
        ) {
            if (o.getDateTest().equals(date)) {
                ModelMapper modelMapper = new ModelMapper() ;
                OperationDto operationDto= modelMapper.map(o, OperationDto.class);
                list_dto.add(operationDto) ;
            }
            else {
                throw new EntityNotFoundException("date non existant ");
            }
        }
        return list_dto ;
    }

    @Override
    public List<OperationDto> getHistoriqueByDate(Date date) {
        ArrayList<Operation> operations = (ArrayList<Operation>) this.getOperations();
        ArrayList<OperationDto> list_dto = new ArrayList<OperationDto>() ;
        for (Operation o:operations
        ) {
            if (o.getDateTest().equals(date)) {
                ModelMapper modelMapper = new ModelMapper() ;
                OperationDto operationDto= modelMapper.map(o, OperationDto.class);
                list_dto.add(operationDto) ;
            }
            else {
                throw new EntityNotFoundException("date non existant ");
            }
        }
        return list_dto ;
    }


}
