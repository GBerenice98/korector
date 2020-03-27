package com.projet.korector.repository;

import com.projet.korector.entity.User;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase

class UserRepositoryTest {



    @Autowired
    UserRepository repository;
    private User user;
    @BeforeEach
    void setUp() {
        user= new User("adiagne","Awa","diagne",
                "adiagne@live.fr","adiagne97");
    }

    @AfterEach
    void tearDown() {
        user=null;
    }



}