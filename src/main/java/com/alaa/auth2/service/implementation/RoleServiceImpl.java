package com.alaa.auth2.service.implementation;

import com.alaa.auth2.dto.RoleDto;
import com.alaa.auth2.model.Role;
import com.alaa.auth2.repo.RoleRepo;
import com.alaa.auth2.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepo roleRepository ;

    @Override
    public RoleDto saveRole(RoleDto roleDto) {
        ModelMapper modelMapper = new ModelMapper();
        Role role  = modelMapper.map(roleDto, Role.class);
        return modelMapper.map(roleRepository.save(role) , RoleDto.class)  ;
    }
}
