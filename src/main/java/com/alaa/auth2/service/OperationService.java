package com.alaa.auth2.service;

import com.alaa.auth2.dto.OperationDto;
import com.alaa.auth2.model.Operation;

import java.util.Date;
import java.util.List;

public interface OperationService {
    Operation save(Operation operation) ;
    public List<Operation> getOperations() ;
    public List<OperationDto> getOperationsPersonnel() ;
    public List<OperationDto> getOperationsByUser(String username) ;
    public List<OperationDto> getHistoriquePersonnelByClient(String client) ;
    public List<OperationDto> getHistoriqueByClient(String client) ;
    public List<OperationDto> getHistoriquePersonnelByDate(Date date) ;
    public List<OperationDto> getHistoriqueByDate(Date date) ;

}
