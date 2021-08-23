package com.alaa.auth2.service;

import com.alaa.auth2.dto.ResponseDto;
import com.alaa.auth2.dto.UserDto;
import com.alaa.auth2.model.User;
import javassist.NotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDto saveUser (UserDto user) ;
    Object addRoleToUser(String username , String roleName) ;
    User getUser(String username) ;
    List<UserDto> getUsers() ;
    String getCurrentUsername() ;
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException ;
    UserDto findByUsername(String username ) ;
    Optional<User> findById (Integer id) ;
    ResponseDto delete(Integer id) throws NotFoundException;
    public ResponseDto resetPassword(Integer id,  String password) throws NotFoundException ;
    public User findByName(String name) ;
}