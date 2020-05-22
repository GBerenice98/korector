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



/*
    public SonarResults getLastNote(){

    } */

/*
    public ResponseEntity<SonarResults> updateResultsByProjectSession(SonarResults sonarResults,Long projectId, Long sessionId){
        log.info("Donnes before" + sonarResults.toString() );
       SonarResults newSonarR = new SonarResults(sonarResults.getId(),sonarResults.getBugs(),
                sonarResults.getVuls(),
                sonarResults.getDebt(),sonarResults.getSmells(),sonarResults.getCoverage(),
                sonarResults.getDups(),sonarResults.getDupsBlock());

        //  newSonarR.getResultsSonarProjects().add(this.projectRepository.findById(projectId).get()));
      //  Project project = projectRepository.findById(projectId).get();
       // Session session = sessionService.getSessionById(sessionId);
       // newSonarR.setResultsSonarProjects(project);
       // newSonarR.setResultsSonarSessions(session);
        System.out.println("Donnes After" + newSonarR.toString() );

        if (!sonarResultsRepository.existsById(sonarResults.getId())) {
            return new ResponseEntity<SonarResults>(HttpStatus.NOT_FOUND);
        }

        SonarResults book =sonarResultsRepository.save(newSonarR);
        if (book != null) {
           // BookDTO bookDTO = mapBookToBookDTO(book);
            return new ResponseEntity<SonarResults>(book, HttpStatus.OK);
        }
        return new ResponseEntity<SonarResults>(HttpStatus.NOT_MODIFIED);




     //   return new ResponseEntity<SonarResults>(this.sonarResultsRepository.saveAndFlush(newSonarR), HttpStatus.OK);

    }
/*
    public Long getIdByProject(Long projectId){/
        //    this.projectRepository.findById(pr)
        return new HashSet<Project>(this.sessionRepository.findById(sessionId).get().getProjects());

        return null;

    }
    public Long getIdBySession(Long sessionId){
        return null;

    }

    public SonarResults findSResultsById(Long id){
       return sonarResultsRepository.findById(id).orElse(null);;
    }
} */


   /* public void setSonarResultsProjects(Long sResultsId,Set<Project> projects)
    {
        Set<Project> putProjects= new HashSet<>();
        projects.forEach(project->{
            putProjects.add(this.projectRepository.findById(project.getId()).get());
        });

        SonarResults putSresults=this.sonarResultsRepository.findById(sResultsId).get();
        putSresults.setResultsSonarProjects(putProjects);

        putProjects.forEach(project -> {
            project.getSo().add(putSession);
            this.projectRepository.saveAndFlush(project);
        });

        this.sessionRepository.saveAndFlush(putSession);
    } */
}
