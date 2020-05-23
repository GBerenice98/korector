package com.projet.korector.controller;

import com.projet.korector.entity.Project;
import com.projet.korector.entity.User;
import com.projet.korector.security.services.UserDetailsImpl;
import com.projet.korector.services.ProjectService;
import com.projet.korector.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class ProjectController {

    final static Logger log = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private ProjectService service;
    @Autowired
    private UserService userService;
    private UserService userController;

    @RequestMapping(value = "/createProject", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void createProject(@Valid @RequestBody Project projet) {
         service.createProject(projet);
    }

    @RequestMapping(value = "/allProjects", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Project> getAllProjects() { return service.getAllProjects(); }

    @RequestMapping(value = "/projectById/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Project> getProjectByUser(@PathVariable Long userId)
    {
        return null;
    }

    @RequestMapping(value = "/getProjectById/{projectId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)

    public  Optional<Project> getProjectByiD(@PathVariable ("projectId") Long projectId)
    {

            return  service.getProjectById(projectId);

    }


    @RequestMapping(value = "/allProjectBySession/{sessionId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Project> getProjectBySession(@PathVariable Long sessionId)
    {
        return service.getProjectBySession(sessionId);
    }

    @RequestMapping(value = "/project/{ProjectId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Project> getProject(@PathVariable Long ProjectId)
    {
         return service.getProjectById(ProjectId);
    }

    @RequestMapping(value = "/deleteProject/{ProjectId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteProject(@PathVariable Long ProjectId)
    {
        service.deleteProject(ProjectId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getProject/{userId}")
    public @ResponseBody
        List<Project> getProjects(@PathVariable Long userId) {

        User user = userService.findById(userId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        System.out.println(authentication.getPrincipal());
        //User currentUser = this.userController.findById(userDetails.getId());

        RestTemplate restTemplate = new RestTemplate();
        String Url = "https://api.github.com/users/"+user.getUsername()+"/repos?visibility=all";
        List<LinkedHashMap<String,String>> repos = restTemplate.getForObject(Url, List.class);
            for(LinkedHashMap<String,String>  repo : repos)
            {
                boolean existByUrl = service.existsByUrl(repo.get("html_url"));
                if (!existByUrl) {

                    Project p = new Project(repo.get("name"), repo.get("description"), repo.get("html_url"), repo.get("created_at"));
                    p.setNote(0f);
                    p.setUser(user);

                    createProject(p);
                }
            }
            List<Project> projects = service.getProjectByUser(user);
            return projects;
    }


}
