package com.projet.korector.services.implem;

import com.projet.korector.entity.User;
import com.projet.korector.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.projet.korector.services.interfaces.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements IUser {
    @Autowired
    private UserRepository userDAO;

    @Override
    public User saveUser(User user) {
        return userDAO.save(user);

    }

    @Override
    public User updateUser(User user) {
        return userDAO.save(user);
    }

    @Override
    public void deleteUser(Long userID) {
      userDAO.deleteById(userID);

    }


    @Override
    public List<User> findAllUser() {
        return userDAO.findAll();
    }

    @Override
    public User findById(Long userID) {
        return userDAO.findById(userID).get();

      //  return userDAO.findById(userID).get();
    }

    @Override
    public User findByUsername(String username) {
        return userDAO.findByUsername(username);

    }


}
