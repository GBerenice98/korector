package com.projet.korector.services;

import com.projet.korector.entity.Project;
import com.projet.korector.model.ProjectImp;
import com.projet.korector.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Project> getProjectByUser(Long userId) {
        return null;
    }

    public List<Project> getProjectBySession(Long sessionId) {

        return null;
    }

    public void deleteProject(Long projectId) {
        repository.deleteById(projectId);
    }

    public Project getProjectById(Long projectId) {
        return (Project) repository.findAllById(projectId);
    }
}
