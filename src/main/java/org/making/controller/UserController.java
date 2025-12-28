package org.making.controller;

import java.util.List;

import org.making.DTO.request.RegisterReq;
import org.making.DTO.response.UserResp;
import org.making.service.UserService;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed("ADMIN")
public class UserController {

    @Inject
    UserService userService;

    @GET
    public List<UserResp> getAll(){
        return userService.getAll();
    }
    @GET
    @RolesAllowed({"ADMIN", "USER"})
    @Path("/{id}")
    public UserResp getById(@PathParam("id") Integer id){
        return userService.getById(id);
    }
    @POST
    public UserResp create(@Valid RegisterReq request){
        return userService.create(request);
    }
    @PUT
    @Path("/{id}")
    public UserResp update(@PathParam("id") Integer id, @Valid RegisterReq request){
        return userService.update(id, request);
    }
    @DELETE
    @Path("/{id}")
    public String delete(@PathParam("id") Integer id){
        return userService.delete(id);
    }

}
