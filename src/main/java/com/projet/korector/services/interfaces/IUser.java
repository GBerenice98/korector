package com.projet.korector.services.interfaces;

import com.projet.korector.entity.User;
import com.projet.korector.modelDTO.UserDTO;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public
interface IUser  {

    User saveUser(User user);
    User updateUser(User user);

    void deleteUser(Long userID);

    List<User> findAllUser();
   User findById(Long userID);
    User findByUsername(String username);



}