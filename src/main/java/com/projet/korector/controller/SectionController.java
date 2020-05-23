package com.projet.korector.controller;
import com.projet.korector.entity.ERole;
import com.projet.korector.entity.Role;
import com.projet.korector.entity.Section;
import com.projet.korector.entity.User;
import com.projet.korector.repository.UserRepository;
import com.projet.korector.services.SectionService;
import com.projet.korector.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/")
public class SectionController {

    final static Logger log = LoggerFactory.getLogger(SectionController.class);

    @Autowired
    private SectionService service;
    @Autowired
    private UserService userService;

    @PostMapping(value = "/createSection" )
    public String createSection(@RequestBody Section section) {
        System.out.println("Création de la section"+ section.toString());
        String response ="ERROR !!";
        if(service.existByName(section.getName())){
            response = "This section name already exist ";
        }
        else{
            service.createSection(section);
            response = "The section is created successfully";
        }

        return response;
    }

    @RequestMapping(value = "/allSections", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Section> getAllSections() { return service.getAllSections(); }

    @DeleteMapping(value = "/deleteSection/{sectionId}")
    public void deleteSection(@PathVariable Long sectionId)
    {
        List<User> users =  userService.findAllUser();
        if(users.isEmpty()){
            service.deleteSection(sectionId);
        }
        else{
            System.out.println("Impossible de supprimer la section, des utilisateurs y sont rattachés");
        }

    }

    @RequestMapping(value = "/SectionByName/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Section getSectionByName(@PathVariable String name)
    {
        return service.getSectionByName(name);
    }

    @RequestMapping(value = "/SectionById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Section getSectionById(@PathVariable Long id)
    {
        return service.getSectionById(id);
    }

    @RequestMapping(value = "/Section/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getStudents(@PathVariable Long id){
        System.out.println("dans la methode ");
        List<User> users =  userService.findAllUser();
        List<User> result = new ArrayList<User>();
        for (User u:users) {
            for (Role role : u.getRoles()) {
                if (role.getName().toString() == ("ROLE_ETUDIANT")) {
                    for (Section s : u.getSections()) {
                        if (s.getId() == id) {

                            result.add(u);
                        }
                    }
                }
            }
        }
        return result;
    }

    @RequestMapping(value = "/SectionTeachers/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getTeachers(@PathVariable Long id){
        System.out.println("dans la methode ");
        List<User> users =  userService.findAllUser();
        List<User> result = new ArrayList<User>();
        for (User u:users) {
            for (Role role : u.getRoles()) {
                if (role.getName().toString() == ("ROLE_ENSEIGNANT")) {
                    for (Section s : u.getSections()) {
                        if (s.getId() == id) {

                            result.add(u);
                        }
                    }
                }
            }
        }
        return result;
    }


}
