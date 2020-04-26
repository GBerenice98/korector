package com.projet.korector.repository;

import java.util.Optional;

import com.projet.korector.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
    User findUserByEmailIgnoreCase(String email);
    Optional<User> findByEmail(String email);
    

}
