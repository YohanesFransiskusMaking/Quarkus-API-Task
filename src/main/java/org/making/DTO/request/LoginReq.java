package org.making.DTO.request;

import jakarta.validation.constraints.NotBlank;

public class LoginReq {
    @NotBlank
    public String name;
    @NotBlank
    public String password;

}
