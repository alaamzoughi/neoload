package com.alaa.auth2.repo;

import com.alaa.auth2.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role , Long> {
    Role findByName(String name) ;

    @Query(value="select role_id from user_roles ur where ur.user_id=userId and  ur.role_id=roleId ;", nativeQuery=true)
    Integer existingRole(Optional<Integer> userId , Optional<Integer> RoleId );



}
