package com.projet.korector.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.projet.korector.entity.*;
import com.projet.korector.security.services.UserDetailsImpl;
import com.projet.korector.services.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class SessionController {

    final static Logger log = LoggerFactory.getLogger(SessionController.class);

    @Autowired
    private SessionService service;

    @Autowired
    private UserController userController;


    @PostMapping("/all")
    @RequestMapping(value = "/createSession", method = RequestMethod.POST,consumes = "application/json",produces = "application/json")
    public ResponseEntity<Session> createSession(@RequestBody SessionImp sessionImp)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User currentUser = this.userController.findById(userDetails.getId());
        return service.createSession(sessionImp,currentUser);
    }

    @PutMapping("/all")
    @RequestMapping(value = "/updateSession", method = RequestMethod.PUT,consumes = "application/json",produces = "application/json")
    public Session updateSession(@RequestBody SessionImp sessionImp)
    {
        return service.updateSession(sessionImp);
    }

    @GetMapping("/all")
    @RequestMapping(value = "/allSessions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Session> getAllSessions()
    {
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.enable(SerializationFeature.INDENT_OUTPUT);
//        String json="";
//        try {
//            json = mapper.writeValueAsString(service.getAllSessions());
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
        return service.getAllSessions();
    }

    @GetMapping("/all")
    @RequestMapping(value = "/sessionById/{sessionId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Session getSessionById(@PathVariable("sessionId") Long sessionId)
    {
        return service.getSessionById(sessionId);
    }

    @GetMapping("/all")
    @RequestMapping(value = "/getSessionsDepot", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<Session> getSessionWithDateDepotNotNull()
    {
        return service.getSessionWithDateDepotNotNull();
    }

    @GetMapping("/all")
    @RequestMapping(value = "/sessionProjects/{sessionId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<Project> getSessionProjects(@PathVariable("sessionId") Long sessionId)
    {
        return service.getSessionProjects(sessionId);
    }

//    @GetMapping("/all")
//    @RequestMapping(value = "/sessionCriterias/{sessionId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public Set<Criteria> getSessionCriterias(@PathVariable("sessionId") Long sessionId)
//    {
//        return service.getSessionCriterias(sessionId);
//    }

    @GetMapping("/all")
    @RequestMapping(value = "/user/sessions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<Session> getAllSessionsByUser()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User currentUser = this.userController.findById(userDetails.getId());

        return service.getAllSessionsByUser(currentUser);
    }

    @GetMapping("/all")
    @RequestMapping(value = "/getSessionCriteres/{sessionId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<SessionCritere> getSessionCriteres(@PathVariable("sessionId")Long sessionId)
    {
        return  service.getSessionCriteres(sessionId);
    }

    @GetMapping("/all")
    @RequestMapping(value = "/addProjectToSession/{sessionId}/{projectId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Long addProjectToSession(@PathVariable("sessionId") Long sessionId, @PathVariable("projectId") Long projectId)
    {
        log.info("dans controller de add");
        System.out.println("dans controller de add");
        service.addProjectToSession(sessionId,projectId);
        return 1L;
    }

    @DeleteMapping("/all")
    @RequestMapping(value = "/deleteProjectFromSession/{sessionId}/{projectId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteProjectFromSession(@PathVariable("sessionId") Long sessionId, @PathVariable("projectId") Long projectId)
    {
        service.deleteProjectFromSession(sessionId,projectId);
    }

    @PostMapping("/all")
    @RequestMapping(value = "/setSessionProjects/{sessionId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void setSessionProjects(@PathVariable Long sessionId,@RequestBody Set<Project> projects)
    {
        service.setSessionProjects(sessionId,projects);
    }

//    @PostMapping("/all")
//    @RequestMapping(value = "/setSessionCriterias/{sessionId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    public void setSessionCriterias(@PathVariable Long sessionId,@RequestBody Set<Criteria> criterias)
//    {
//        service.setSessionCriterias(sessionId,criterias);
//    }

    @DeleteMapping("/all")
    @RequestMapping(value = "/deleteSession/{sessionId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteSession(@PathVariable("sessionId") Long sessionId)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User currentUser = this.userController.findById(userDetails.getId());
        service.deleteSession(sessionId,currentUser);
    }

    @DeleteMapping("/all")
    @RequestMapping(value = "/deleteAllSessions", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteAllSessions()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User currentUser = this.userController.findById(userDetails.getId());
        service.deleteAllSessions(currentUser);
    }

    @GetMapping("/all")
    @RequestMapping(value = "/getSessionRuns/{sessionId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<Run> getSessionRuns(@PathVariable("sessionId") Long sessionId)
    {
        return service.getSessionRuns(sessionId);
    }

    @GetMapping("/all")
    @RequestMapping(value = "/exportCSV/{runId}", method = RequestMethod.GET)
    public void exportCSV(@PathVariable("runId") Long runId,HttpServletResponse response) {
        service.exportCSV(runId,response);
    }

}
