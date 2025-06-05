package com.cloudfleet360.auth_service.dto;

import com.cloudfleet360.auth_service.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    private Role role = Role.USER; // default to USER unless explicitly ADMIN
}
