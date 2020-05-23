package com.projet.korector.services;

import com.projet.korector.entity.Project;
import com.projet.korector.entity.Session;
import com.projet.korector.entity.User;
import com.projet.korector.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository repository;

    public Project createProject(Project project) {
        return repository.save(project);
    }

    public List<Project> getAllProjects() {

        return repository.findAll();
    }

    public List<Project> getProjectByUser(User user) {

        return repository.findByUser(user);
    }

    public List<Project> getProjectBySession(Long sessionId) {
return null;


    }

    public void deleteProject(Long projectId) {
        repository.deleteById(projectId);
    }


    public Optional<Project> getProjectById(Long projectId) {
        return repository.findById(projectId);
    }


    /*public Project ProjectById(Long projectId) {
        return this.repository.findById(projectId).orElse(null);
    } */


    public boolean existsByUrl(String html_url) {
        return repository.existsByUrl(html_url);
    }
}
