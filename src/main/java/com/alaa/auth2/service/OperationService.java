package com.alaa.auth2.service;

import com.alaa.auth2.model.Operation;

import java.util.List;
import java.util.Optional;

public interface OperationService {
    Operation save(Operation operation) ;
    public List<Operation> getOperations() ;

}
