package com.alaa.auth2.service.implementation;

import com.alaa.auth2.dto.ResponseDto;
import com.alaa.auth2.dto.UserDto;
import com.alaa.auth2.exception.ExistingEntityException;
import com.alaa.auth2.model.Role;
import com.alaa.auth2.model.User;
import com.alaa.auth2.repo.RoleRepo;
import com.alaa.auth2.repo.UserRepo;
import com.alaa.auth2.service.UserService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {



    @Autowired
    private   UserRepo userRepo ;
    @Autowired
    private  RoleRepo roleRepo ;
    @Autowired
    private PasswordEncoder passwordEncoder ;

    @Override
    public UserDto saveUser(UserDto userDto) {


            String username = userDto.getUsername() ;
            Optional<User> storedUser = Optional.ofNullable(userRepo.findByUsername(username));
            if (storedUser.isPresent()) {
                return null ;
            }
            else {
                ModelMapper modelMapper = new ModelMapper();
                User user  = modelMapper.map(userDto, User.class);
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                return modelMapper.map(userRepo.save(user) , UserDto.class)  ;
            }


    }



    @Override
    public ResponseDto addRoleToUser(String username, String roleName) {



        User user = userRepo.findByUsername(username) ;
        Role role = roleRepo.findByName(roleName) ;
        if(user.getRoles().contains(role)) {
            return null ;
        }
        else {
            user.getRoles().add(role) ;
            return ResponseDto.builder()
                    .message("sucess")
                    .build() ;
        }

    }
    @Override
    public User getUser(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public List<UserDto> getUsers() {
        ModelMapper modelMapper = new ModelMapper();
        List<User> users =  userRepo.findAll();
        List<UserDto> userDtos = new ArrayList<UserDto>() ;
        for (User u: users
             ) {
             UserDto userDto = modelMapper.map(u, UserDto.class);
             userDtos.add(userDto) ;
        }
        return userDtos ;
    }

    @Override
    public String getCurrentUsername() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        return username ;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user = userRepo.findByUsername(username) ;
       if (user==null) {
           throw new UsernameNotFoundException("user not found") ;

       }
        Collection<SimpleGrantedAuthority> authorities= new ArrayList<>() ;
       user.getRoles().forEach(role -> { authorities.add(
               new SimpleGrantedAuthority(role.getName()));
       }
       );
        return new org.springframework.security.core.userdetails.User(user.getUsername() , user.getPassword() ,authorities);
    }

    @Override
    public UserDto findByUsername(String username) {
        ModelMapper modelMapper = new ModelMapper();
        List<User> users =  userRepo.findAll();
        User ourUser = null;
        for (User u: users ) {
            if (u.getUsername().equals(username)) {
                ourUser = u ;
            }
        }
        UserDto userDto = modelMapper.map(ourUser, UserDto.class);
        return  userDto ;
    }

    @Override
    public User findByName(String name) {
        return userRepo.findByName(name);
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userRepo.findById(id);
    }

    @Override
    public ResponseDto delete(Integer id) throws NotFoundException {
        Optional<User> user =  this.findById(id) ;
        if (!(user.isPresent())) {
            throw new  NotFoundException("User not found") ;
        }
        else  {
            userRepo.deleteById(id);
            return ResponseDto.builder()
                    .message("sucess")
                    .build() ;

        }

    }

    public ResponseDto resetPassword(Integer id,  String password) throws NotFoundException {
        Optional<User> user = this.findById(id);
        if (user.isPresent()) {
            return ResponseDto.builder()
                    .message("sucess")
                    .build();
        } else {
            throw new NotFoundException("not found user ");
        }

    }
}
