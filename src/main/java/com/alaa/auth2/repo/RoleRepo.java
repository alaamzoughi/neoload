package com.alaa.auth2.repo;

import com.alaa.auth2.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role , Long> {
    Role findByName(String name) ;





}
