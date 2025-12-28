package org.making.repository;

import java.util.Optional;

import org.making.entity.User;


import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepositoryBase<User, Integer> {

    public Optional<User> findByUsername(String name){
        return find("name", name).firstResultOptional();
    }

    public boolean existByUsername(String name){
        return count("name", name) > 0;
    }

}
