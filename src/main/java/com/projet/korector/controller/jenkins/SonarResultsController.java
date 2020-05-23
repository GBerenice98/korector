package com.projet.korector.controller.jenkins;

import com.projet.korector.controller.SessionController;
import com.projet.korector.entity.Project;
import com.projet.korector.entity.Session;
import com.projet.korector.entity.SonarResults;
import com.projet.korector.payload.request.UserRequest;
import com.projet.korector.payload.response.MessageResponse;
import com.projet.korector.repository.ProjectRepository;
import com.projet.korector.repository.SessionRepository;
import com.projet.korector.repository.SonarResultsRepository;
import com.projet.korector.services.ProjectService;
import com.projet.korector.services.SessionService;
import com.projet.korector.services.SonarResultsService;
import com.projet.korector.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/sonarResults")

public class SonarResultsController {


    @Autowired
    private SonarResultsService sonarResultsService;



    @Autowired

    private ProjectService projectService;
    @Autowired
    private SessionService sessionService;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private SessionController sessionController;

    @Autowired
    private SonarResultsController sonarResultsController;

    final static Logger log = LoggerFactory.getLogger(SessionController.class);

    @RequestMapping(value = "/saveResults", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<?> saveResults(@RequestBody SonarResults sonarResults){
        sonarResultsService.saveSonarResults(sonarResults);
        return ResponseEntity.ok(new MessageResponse("Results sonar save!"));

    }

    @GetMapping("/all")
    @RequestMapping(value = "/getResultsBySessProj/{sessionId}/{projectId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public SonarResults getLastResultsSonar(@PathVariable("sessionId") Long sessionId, @PathVariable("projectId") Long projectId){
        return sonarResultsService.getLastBuildResults(sessionId,projectId);

    }

    @GetMapping("/all")
    @RequestMapping(value = "/runExistsSession/{sessionId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean runExistsBySession(@PathVariable("sessionId") Long sessionId){
        return  sonarResultsService.runExistsBySession(sessionId);

    }

    @GetMapping("/all")
    @RequestMapping(value = "/runExistsSessionProject/{sessionId}/{projectId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean runExistsBySessionProject(@PathVariable("sessionId") Long sessionId, @PathVariable("projectId")  Long projectId){

        return  sonarResultsService.runExistsBySessionProject(sessionId,projectId);

    }

    @GetMapping("/all")
    @RequestMapping(value = "/getAllResultsProjects/{projectId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SonarResults> getAllResultsProjects( @PathVariable("projectId")  Long projectId) {
        List <SonarResults> results = new ArrayList<>();

        List<Session> sessions = sessionController.getSessionDepotByProjectId(projectId);
        if (!sessions.isEmpty()) {

            for (Session session : sessions) {
                Long sessionId = session.getId();
                if (runExistsBySessionProject(sessionId, projectId)) {
                    SonarResults lastResults = sonarResultsController.getLastResultsSonar(sessionId, projectId);
                    results.add(lastResults);
                }

            }

        }
return results;
    }



}
