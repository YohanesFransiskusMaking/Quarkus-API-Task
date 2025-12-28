package org.making.service;

import java.util.ArrayList;
import java.util.List;

import org.making.DTO.request.RegisterReq;
import org.making.DTO.response.UserResp;
import org.making.entity.User;
import org.making.repository.UserRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;

    public List<UserResp> getAll(){
        List<User> users = userRepository.findAll().list();
        List<UserResp> resps = new ArrayList<>();
        for(User user : users){
            resps.add(mapToResponse(user));      
        }
        return resps;

    }
    
    public UserResp getById(Integer id){
        User user = userRepository.findByIdOptional(id)
        .orElseThrow(()-> new NotFoundException("user dengan id "+id+" tidak ditemukan"));
        
        return mapToResponse(user);
    }

    @Transactional
    public UserResp create(RegisterReq request){
        User user = new User();
        user.setName(request.name);
        user.setPassword(request.password);
        user.setRole(request.role);
        userRepository.persist(user);
        return mapToResponse(user);
    }
    @Transactional
    public UserResp update(Integer id, RegisterReq request){
        User getUser = userRepository.findByIdOptional(id).orElseThrow(()-> new NotFoundException("data tidak ditemukan"));

        getUser.setName(request.name);
        getUser.setPassword(request.password);
        getUser.setRole(request.role);

        return mapToResponse(getUser);
    }
    @Transactional
    public String delete(Integer id){
        User user = userRepository.findByIdOptional(id)
        .orElseThrow(()-> new NotFoundException("user dengan id "+id+" tidak ditemukan"));
        userRepository.delete(user);
        return "User dengan id "+id+" berhasil dihapus";
    }

    private UserResp mapToResponse(User user){
        UserResp resp = new UserResp();
        resp.id = user.getId();
        resp.name = user.getName();
        resp.role = user.getRole().toString();
        return resp;
    }



}
