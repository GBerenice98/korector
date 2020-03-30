package com.projet.korector.repository;

import com.projet.korector.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {

    Project findAllById(Long projectId);
}
