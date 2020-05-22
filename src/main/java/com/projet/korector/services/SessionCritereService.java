package com.projet.korector.services;

import com.projet.korector.controller.SessionController;
import com.projet.korector.entity.Project;
import com.projet.korector.entity.Session;
import com.projet.korector.entity.SessionCritere;
import com.projet.korector.entity.SessionCritereImp;
import com.projet.korector.repository.SessionCritereRepository;
import com.projet.korector.repository.SessionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionCritereService {

    final static Logger log = LoggerFactory.getLogger(SessionController.class);

    @Autowired
    private SessionCritereRepository sessionCritereRepository;

    @Autowired
    private SessionRepository sessionRepository;


    public SessionCritereImp createSessionCritere(SessionCritereImp sessionCritereImp)
    {
        SessionCritere sessionCritere = new SessionCritere(
                sessionRepository.findById(sessionCritereImp.getSessionId()).get(),
                sessionCritereImp.getCritereId(),
                sessionCritereImp.getName(),
                sessionCritereImp.getHeight(),
                sessionCritereImp.getValue(),
                sessionCritereImp.getSeuil(),
                sessionCritereImp.getType()
        );
        this.sessionCritereRepository.save(sessionCritere);
        sessionCritereImp.setId(sessionCritere.getId());
        return sessionCritereImp;
    }

    public void updateSessionCritere(Long id,Long height,Long seuil,Float value)
    {
        log.info("SessionCritereImpl de update : "+id+","+height+","+seuil+","+value);

        SessionCritere sessionCritere= sessionCritereRepository.findById(id).get();
        sessionCritere.setHeight(height);
        sessionCritere.setSeuil(seuil);
        sessionCritere.setValue(value);

        this.sessionCritereRepository.save(sessionCritere);
    }

    public SessionCritere getSessionCritereById(Long id)
    {
        return  this.sessionCritereRepository.findById(id).get();
    }

    public List<SessionCritere>  getAllSessionCritere()
    {
        return this.sessionCritereRepository.findAll();
    }

    public void deleteSessionCritere(Long sessionId,Long critereId)
    {
        SessionCritere deletedSC=this.sessionCritereRepository.findById(critereId).get();
        Session putSession=this.sessionRepository.findById(sessionId).get();

        putSession.getSessionCriteres().remove(deletedSC);
        this.sessionCritereRepository.deleteById(critereId);
        this.sessionRepository.saveAndFlush(putSession);
    }
}
