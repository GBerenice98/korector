package com.projet.korector.repository;

import com.projet.korector.entity.User;
import com.projet.korector.modelDTO.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
  User findByUsername(String username);

}
