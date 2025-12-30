package org.making.controller;

import java.util.List;

import org.making.DTO.response.UserResp;
import org.making.service.UserService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/public/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class test {

    @Inject
    UserService userService;

    @GET
    public List<UserResp> getAll() {
        return userService.getAll();
    }
}


