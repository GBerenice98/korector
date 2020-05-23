package com.projet.korector.repository;

import com.projet.korector.entity.Project;
import com.projet.korector.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {

    Optional<Project> findById(Long projectId);

    boolean existsByUrl(String html_url);


    List<Project> findByUser(User user);

}
