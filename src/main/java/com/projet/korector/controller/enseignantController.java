package com.projet.korector.controller;
import com.projet.korector.services.implem.EnseignantServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
@RequestMapping(value = "/api/enseignant")
public class enseignantController {

    @Autowired
    private EnseignantServiceImp service;

    @RequestMapping(value = "/getAllClasse", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public void getAllClasse(Integer id_ense) {
         service.getAllClasse(id_ense);


    }

    @RequestMapping(value = "/isEnseignantClasse", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)

    public boolean isEnseignantClasse(Integer idClasse) {
        return service.isEnseignantClasse(idClasse);


    }
}
