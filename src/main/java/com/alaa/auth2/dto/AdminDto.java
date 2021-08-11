package com.alaa.auth2.dto;


import lombok.*;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Builder
public class AdminDto {
    private Integer id;
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "email is required")
    @Email
    private String email;
    @NotBlank(message = "photo is required")
    private String photo;
    @NotBlank(message = "role is required")
    private RoleDto role ;


}
