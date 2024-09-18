package com.adelsonsljunior.repositories;

import com.adelsonsljunior.entities.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

    public Optional<User> findByEmail(String email) {
        return find("email", email).firstResultOptional();
    }

    public Optional<User> findByUsername(String username) {
        return find("username", username).firstResultOptional();
    }
}
