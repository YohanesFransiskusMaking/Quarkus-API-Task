package org.making.DTO.request;

import org.making.entity.Role;

import jakarta.validation.constraints.NotBlank;

public class RegisterReq {
    @NotBlank
    public String name;
    @NotBlank
    public String password;
    public Role role;
}
