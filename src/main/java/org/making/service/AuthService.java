package org.making.service;

import java.time.Duration;
import java.util.Set;
import org.making.DTO.request.LoginReq;
import org.making.DTO.response.TokenResp;
import org.making.entity.User;
import org.making.repository.UserRepository;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotAuthorizedException;

@ApplicationScoped
public class AuthService {
    @Inject
    UserRepository userRepository;

    public TokenResp token(LoginReq request){
        User user = userRepository.findByUsername(request.name)
        .orElseThrow(()-> new NotAuthorizedException("username atau password salah"));

        if(!user.getPassword().equals(request.password)){
            throw new NotAuthorizedException("username atau password salah");
        }

        String token = Jwt.issuer("task-api")
        .subject(user.getName())
        .groups(Set.of(user.getRole().name()))
        .expiresIn(Duration.ofHours(1))
        .sign();

        TokenResp resp = new TokenResp();
        resp.token = token;
        return resp;
    }



}
