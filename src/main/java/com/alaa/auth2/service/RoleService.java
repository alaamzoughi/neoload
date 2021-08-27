package com.alaa.auth2.service;

import com.alaa.auth2.dto.RoleDto;
import com.alaa.auth2.model.Role;

public interface RoleService {

    RoleDto saveRole (RoleDto roleDto) ;
    Role findByName (String name) ;
}
