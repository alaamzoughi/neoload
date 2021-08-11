package com.alaa.auth2.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class UserDto {

    private Integer id;
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "email is required")
    @Email
    private String username;
    @NotBlank(message = "photo is required")
    private String photo;
    private List<RoleDto> roles;
    private String password;




}
