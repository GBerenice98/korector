package com.projet.korector.services;

import com.projet.korector.controller.SessionController;
import com.projet.korector.entity.Project;
import com.projet.korector.entity.Session;
import com.projet.korector.entity.SonarResults;
import com.projet.korector.entity.User;
import com.projet.korector.repository.ProjectRepository;
import com.projet.korector.repository.SessionRepository;
import com.projet.korector.repository.SonarResultsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class SonarResultsService {

    @Autowired
    private SonarResultsRepository sonarResultsRepository;
    @Autowired

    private ProjectService projectService;
    @Autowired
    private SessionService sessionService;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private SessionRepository sessionRepository;

    final static Logger log = LoggerFactory.getLogger(SessionController.class);

    public SonarResults saveSonarResults(SonarResults sonarResults){

        SonarResults sonar= new SonarResults(sonarResults.getBugs(),
                sonarResults.getVuls(),
                sonarResults.getDebt(),sonarResults.getSmells(),
                sonarResults.getDups(),sonarResults.getDups_block(),sonarResults.getProjectId(),sonarResults.getSessionId(),sonarResults.getNote_finale()
                ,sonarResults.getDate());

        System.out.println("Sonar Results " + sonar);
        log.info("created Sonar results : "+ sonar);

        sonarResultsRepository.saveAndFlush(sonar);
        return sonar;


    }




    public List<SonarResults> getAllResultsBySessionProject(Long projectId, Long sessionId){

          List <SonarResults> sonarResults =  sonarResultsRepository.findBySessionIdAndProjectId(projectId,sessionId);
        if (!sonarResults.isEmpty()) {
            return sonarResults;
        }else {
            return null;
        }

    }


    public SonarResults getLastBuildResults( Long sessionId , Long projectId){
        List <SonarResults> sonarResults =  sonarResultsRepository.findBySessionIdAndProjectId(sessionId , projectId);
        SonarResults lastResults = sonarResults.get(0);

        int index = 0;
        for(int i=1; i < sonarResults.size(); i++){

            if(sonarResults.get(i).getDate().isAfter(sonarResults.get(i-1).getDate())){
                    lastResults =  sonarResults.get(i);
            }
        }
        return lastResults;


    }
    public boolean runExistsBySession(Long sessionId){

      return  sonarResultsRepository.existsBySessionId(sessionId);

    }

    public boolean runExistsBySessionProject(Long sessionId, Long projectId){
       return  sonarResultsRepository.existsBySessionIdAndProjectId(sessionId,projectId);

    }

}
