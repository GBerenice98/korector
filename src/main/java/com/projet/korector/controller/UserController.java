package com.projet.korector.controller;
import com.projet.korector.entity.Section;
import com.projet.korector.entity.Session;
import com.projet.korector.entity.Role;
import com.projet.korector.entity.ERole;
import com.projet.korector.entity.User;
import com.projet.korector.payload.request.UserRequest;
import com.projet.korector.payload.response.MessageResponse;
import com.projet.korector.repository.RoleRepository;
import com.projet.korector.repository.SectionRepository;
import com.projet.korector.repository.UserRepository;
import com.projet.korector.security.services.UserDetailsImpl;
import com.projet.korector.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private UserRepository userRepository;
    Set<Role> roles = new HashSet<>();

    @Autowired
    RoleRepository roleRepository;


    @Autowired
    SectionRepository sectionRepository;

    @Autowired
    PasswordEncoder encoder;
    private AuthenticationManager authenticationManager;


    @RequestMapping(value = "/saveUser/{userRoleId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveUser(@RequestBody UserRequest userRequest , @PathVariable ("userRoleId") Long userRoleId) {
       if (userRepository.existsByUsername(userRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(userRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
        // Create new user's account
        User user = new User(userRequest.getUsername(),
                userRequest.getEmail(),
                encoder.encode(userRequest.getPassword()));

        user.setGithubAccount(userRequest.getGithubAccount());

        Set<Role> roles = new HashSet<>();
        Role userRole;
        if ( userRoleId == 1) {


            userRole = roleRepository.findByName(ERole.ROLE_ETUDIANT)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        }
        else {
                userRole = roleRepository.findByName(ERole.ROLE_ENSEIGNANT)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            }
        roles.add(userRole);
        user.setRoles(roles);

        // Create new section
        List <String> sectionsName = userRequest.getSectionName();
        Set <Section> sections = new HashSet<>();
        for (int i =0;i<sectionsName.size();i++) {
            Section section = sectionRepository.getSectionByName(sectionsName.get(i));
            sections.add(section);
        }
        user.setSections(sections);
        service.saveUser(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));


    }


    @PostMapping(value = "/updateUser")
    public User updateUser(User user) {
        return service.updateUser(user);
    }

    @RequestMapping(value = "/deleteUser/{userId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteById(@PathVariable ("userId") Long userId) {
        service.deleteById(userId);

    }
    @RequestMapping(value = "/findAllStudent", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List <User> >  findAllStudent()
    {
        List<User> allUsers = userRepository.findAll();
        ArrayList<User> studentsResponse = new ArrayList<User>();
        for(User user : allUsers){
            for(Role role : user.getRoles()){
                if(role.getName().toString() == ("ROLE_ETUDIANT")){
                   // usersResponse[allUsers.get()] = new List<User>();
                    studentsResponse.add(user) ;
                }
                }

            }
        return new  ResponseEntity <List <User> >(studentsResponse, HttpStatus.OK);
    }
    @RequestMapping(value = "/findAllProf", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<List <User> > findAllProf()
    {
        List<User> allProfs = userRepository.findAll();
        ArrayList<User> profsResponse = new ArrayList<User>();
        for(User user : allProfs){
            for(Role role : user.getRoles()){
                if(role.getName().toString() == ("ROLE_ENSEIGNANT")){
                    profsResponse.add(user) ;
                }
            }

        }
        return new  ResponseEntity <List <User> >(profsResponse, HttpStatus.OK);

    }
    @RequestMapping(value = "/findAllClasses", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllClasses()
    {
        Integer nb_classes = 0;
        return ResponseEntity.ok(new MessageResponse("Test"));
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)

    public User findById(Long userID) {

        return service.findById(userID);
    }
    @RequestMapping(value = "/findByUsername", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)

    public Optional<User> findByUsername(String username) {
        return service.findByUsername(username);

    }

    @GetMapping("/sessions")
    public ResponseEntity<Set<Session>> getSessions() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        User currentUser = findById(userDetails.getId());
        Set<Session> sessions = currentUser.getSessions();

        return new ResponseEntity<Set<Session>>(sessions, HttpStatus.OK);

    }
    
}


