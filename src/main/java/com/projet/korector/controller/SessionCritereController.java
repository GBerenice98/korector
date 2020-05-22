package com.projet.korector.controller;

import com.projet.korector.entity.SessionCritere;
import com.projet.korector.entity.SessionCritereImp;
import com.projet.korector.services.SessionCritereService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class SessionCritereController {

    final static Logger log = LoggerFactory.getLogger(SessionController.class);

    @Autowired
    private SessionCritereService sessionCritereService;

    @PostMapping("/all")
    @RequestMapping(value = "/createSessionCritere", method = RequestMethod.POST,produces = "application/json")
    public SessionCritereImp createSessionCritere(@RequestBody SessionCritereImp sessionCritereImp)
    {
        return sessionCritereService.createSessionCritere(sessionCritereImp);
    }

    @GetMapping("/all")
    @RequestMapping(value = "/updateSessionCritere/{id}/{height}/{seuil}/{value}", method = RequestMethod.GET)
    public void updateSessionCritere(@PathVariable("id") Long id,@PathVariable("height") Long height,@PathVariable("seuil") Long seuil,@PathVariable("value") Float value)
    {
         sessionCritereService.updateSessionCritere(id,height,seuil,value);
    }

    @GetMapping("/all")
    @RequestMapping(value = "/getSessionCritereById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public SessionCritere getSessionCritereById(Long id)
    {
        return  sessionCritereService.getSessionCritereById(id);
    }

    @GetMapping("/all")
    @RequestMapping(value = "/getAllSessionCritere", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SessionCritere> getAllSessionCritere()
    {
        return sessionCritereService.getAllSessionCritere();
    }

    @DeleteMapping("/all")
    @RequestMapping(value = "/deleteSessionCritere/{sessionId}/{critereId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteSessionCritere(@PathVariable("sessionId") Long sessionId,@PathVariable("critereId") Long critereId)
    {
        this.sessionCritereService.deleteSessionCritere(sessionId,critereId);
    }
}
