package com.alaa.auth2.dto;



import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


import javax.validation.constraints.NotBlank;
import java.util.List;


@Data
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Builder
public class RoleDto {

    private Integer id;
    @NotBlank(message = "role name is required")
    private String name;
    @JsonIgnore
    private List<UserDto> users  ;






}
