package com.projet.korector.controller;

import com.projet.korector.entity.Student;
import com.projet.korector.services.implem.StudentServiceImp;
import com.projet.korector.services.implem.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping(value = "/api/student")
public class studentController {

    @Autowired
    private StudentServiceImp service;

    @RequestMapping(value = "/getAllStudent", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Student> getAllStudent(String idClasse) {

        return service.getAllStudent(idClasse);
    }

    @RequestMapping(value = "/getIdClasse", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer getIdClasse(Student s) {

        return service.getIdClasse(s);
    }


    @RequestMapping(value = "/updateidClasse", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateidClasse(Student s) {

        service.updateidClasse(s);

    }

    @RequestMapping(value = "/updateClasseForAllStudent", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateClasseForAllStudent(Integer idClasse) {
        service.updateClasseForAllStudent(idClasse);

    }

}
