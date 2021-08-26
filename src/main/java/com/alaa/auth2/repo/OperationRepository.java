package com.alaa.auth2.repo;

import com.alaa.auth2.model.Operation;

import com.alaa.auth2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OperationRepository extends JpaRepository<Operation, Integer> {

}
