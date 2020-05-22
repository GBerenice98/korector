package com.projet.korector.controller;

import com.projet.korector.entity.Role;
import com.projet.korector.entity.Section;
import com.projet.korector.entity.User;
import com.projet.korector.payload.response.StatistiqueResponse;
import com.projet.korector.repository.SectionRepository;
import com.projet.korector.repository.UserRepository;
import com.projet.korector.services.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/resource")
public class RessourceController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    SectionRepository sectionRepository;

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/student")
    @PreAuthorize("hasRole('ETUDIANT') or hasRole('ENSEIGNANT') or hasRole('ADMIN')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/prof")
    @PreAuthorize("hasRole('ENSEIGNANT')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }

    @GetMapping("/stats")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> adminStatistique() {
        List<User> allUsers = userRepository.findAll();
        System.out.println("Number of users : " + allUsers.size());
        List<Section> sections = sectionRepository.findAll();

        int nb_students = 0;
        int nb_profs = 0;
        int nb_classes = sections.size();
        System.out.println("count Sections" +nb_classes);

        for(User user : allUsers){
            System.out.println(user.getRoles().size());
            for(Role role : user.getRoles()){
                System.out.println("Role : " + role.getName());
                if(role.getName().toString() == ("ROLE_ETUDIANT")){
                    nb_students ++;
                    System.out.println("count ROLE_ETUDIANT");
                }
                if(role.getName().toString() == ("ROLE_ENSEIGNANT")){
                    nb_profs ++;
                    System.out.println("count ROLE_ENSEIGNANT");
                }
            }

        }

        return ResponseEntity.ok(new StatistiqueResponse(nb_students, nb_profs, nb_classes));
    }
}
