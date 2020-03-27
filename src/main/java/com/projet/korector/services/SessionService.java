package com.projet.korector.services;

import com.projet.korector.entity.Session;
import com.projet.korector.modelDTO.SessionImp;
import com.projet.korector.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    //private ProjectRepository projectRepository;


    public Session createSession(SessionImp sessionImp)
    {

        //sessionRepository.save(session);
        return null;
    }

    public List<Session> getAllSession()
    {
        return sessionRepository.findAll();
    }

    public List<Session> getSessionByUser(Long userId)
    {
        //sessionRepository.findAll().stream().filter(session -> { session.getUser().getId().equals(userId)});
        return null;
    }

//    public List<Project> getSessionProjects()
//    {
//        return
//    }

    public void addProject()
    {
        // l'idSession d'un projet passe de null à l'id de la session
    }

    public void deleteProject(Long id)
    {
        //projectRepository.delete(id);
    }
}
