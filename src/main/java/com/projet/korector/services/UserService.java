package com.projet.korector.services;

import com.projet.korector.entity.User;
import com.projet.korector.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service



public class UserService {
    @Autowired
    UserRepository userRepository;
    public User saveUser(User user) {
        return userRepository.save(user);

    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);

    }


    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    public User findById(Long userID) {
        return userRepository.findById(userID).get();

        //  return userDAO.findById(userID).get();
    }


    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);

    }
//    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmailIgnoreCase(email);
    }


    public List<User> getAllStudent(String idClasse) {

        return null;
    }

    public Integer getIdClasse(User s) {
        return null;
    }


}
