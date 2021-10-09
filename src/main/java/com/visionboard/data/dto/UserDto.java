package com.visionboard.data.dto;

import com.visionboard.data.model.Role;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Data
public class UserDto {
    @NotBlank(message = "Name can't be null")
    @Size(min = 3, max = 60)
    private String name;
    @NotBlank(message = "Age can't ba blank")
    private String age;
    @Email
    @Size(min = 10, max = 60)
    private String email;
    @NotBlank(message = "Password can't be blank")
    @Size(min = 6, max = 60)
    private String password;
    private Role role;
}
