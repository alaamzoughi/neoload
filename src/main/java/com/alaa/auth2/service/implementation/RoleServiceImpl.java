package com.alaa.auth2.service.implementation;

import com.alaa.auth2.dto.RoleDto;
import com.alaa.auth2.model.Role;
import com.alaa.auth2.model.User;
import com.alaa.auth2.repo.RoleRepo;
import com.alaa.auth2.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepo roleRepository ;

    @Override
    public RoleDto saveRole(RoleDto roleDto) {
        Optional<Role> storedRole = Optional.ofNullable(roleRepository.findByName(roleDto.getName()));
        if (storedRole.isPresent()) {
            return null ;
        }
        else {
            ModelMapper modelMapper = new ModelMapper();
            Role role  = modelMapper.map(roleDto, Role.class);
            return modelMapper.map(roleRepository.save(role) , RoleDto.class)  ;
        }

    }

    @Override
    public Role findByName(String name) {
        return null;
    }
}
