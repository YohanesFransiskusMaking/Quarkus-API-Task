package org.making.controller;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.making.DTO.request.LoginReq;
import org.making.DTO.response.TokenResp;
import org.making.security.TokenBlacklist;
import org.making.service.AuthService;

import io.quarkus.security.Authenticated;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/auth") 
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class AuthController {

    @Inject
    AuthService authService;

    @Inject
    JsonWebToken jwt;

    @Inject
    TokenBlacklist blacklist;


    @POST
    @Path("/login")
    @PermitAll
    public TokenResp token(@Valid LoginReq request){
        return authService.token(request);
    }

    @POST
    @Path("/logout")
    @Authenticated
    public String logout(){
        String jti = jwt.getClaim("jti");
        blacklist.blacklist(jti);
        return "logout berhasil";
    }


}
