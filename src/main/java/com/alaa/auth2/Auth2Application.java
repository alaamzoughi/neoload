package com.alaa.auth2;

import com.alaa.auth2.dto.UserDto;
import com.alaa.auth2.fileManagement.FileStorageProperties;
import com.alaa.auth2.model.User;
import com.alaa.auth2.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class   Auth2Application {

    public static void main(String[] args) {
        SpringApplication.run(Auth2Application.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder() ;
    }




    @Bean
    CommandLineRunner run (UserService userService) {
        return args -> {
           userService.saveUser(new UserDto(null ,"ahmed" , "hamidou" , "photo" , null , "123")) ;
        } ;
    }








}
