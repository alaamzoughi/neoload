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
                throw new ExistingEntityException("Username is already used ") ;

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
        /**
        Optional<Integer> role_db_id = Optional.ofNullable(roleRepo.findByName(roleName).getId());
        Optional<Integer> user_db_id = Optional.ofNullable(userRepo.findByUsername(username).getId());
        System.out.println(role_db_id );
        System.out.println(user_db_id );

        Integer existing_role_id = roleRepo.existingRole(user_db_id , role_db_id) ;
        return existing_role_id ;
         **/

        User user = userRepo.findByUsername(username) ;
        Role role = roleRepo.findByName(roleName) ;
        user.getRoles().add(role) ;
        return ResponseDto.builder()
                .message("sucess")
                .build() ;


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
    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
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
