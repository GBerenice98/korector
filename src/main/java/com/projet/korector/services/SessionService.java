package com.projet.korector.services;

import com.projet.korector.controller.SessionController;
import com.projet.korector.controller.UserController;
import com.projet.korector.entity.*;
import com.projet.korector.repository.*;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SessionService {

    final static Logger log = LoggerFactory.getLogger(SessionController.class);

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private RunRepository runRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private CriteriaRepository criteriaRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserController userController;

    @Autowired
    private SessionCritereRepository sessionCritereRepository;

    public ResponseEntity<Session> createSession(SessionImp sessionImp, User currentUser)
    {
        log.info("objet angular reçu pour création :"+sessionImp.toString());

        Session session = new Session(sessionImp.getName(),sessionImp.getDate_depot(),sessionImp.getHeureDepot());

        Set<Project> projects = new HashSet<>();
        sessionImp.getProjects().forEach(projectId-> {
            projects.add(this.projectRepository.findById(projectId).get());
        });

        session.setProjects(new HashSet<>());
        Session createdSession = this.sessionRepository.save(session);

        createdSession.getProjects().addAll(projects);

        log.info("createdSession : "+createdSession);
        currentUser.getSessions().add(createdSession);
        createdSession.getUsers().add(currentUser);

        return new ResponseEntity<Session>(this.sessionRepository.saveAndFlush(createdSession), HttpStatus.OK);
    }

    public Session updateSession(SessionImp sessionImp)
    {
        log.info("Session à modifier : "+sessionImp);
        Session session=new Session(sessionImp.getId(),sessionImp.getName(),sessionImp.getDate_depot(),sessionImp.getHeureDepot());
        sessionImp.getProjects().forEach(projectId-> {
            session.getProjects().add(this.projectRepository.findById(projectId).get());
        });

        sessionImp.getSessionCritere().forEach(sessionCritereId-> {
            session.getSessionCriteres().add(this.sessionCritereRepository.findById(sessionCritereId).get());
        });

        Session newSession=null;
        if(this.sessionRepository.findById(session.getId()).isPresent())
        {

            this.setSessionProjects(session.getId(),session.getProjects());
            this.setSessionCriteres(session.getId(),session.getSessionCriteres());

            newSession=this.sessionRepository.save(session);
        }
        log.info("UpdateSession : "+newSession);
        return newSession;
    }

    public List<Session> getAllSessions()
    {
        return sessionRepository.findAll();
    }

    public Set<Session> getAllSessionsByUser(User currentUser) {

        return sessionRepository.findAll().stream().filter(session -> session.getUsers().contains(currentUser)).collect(Collectors.toSet());
    }

    public Set<Session> getSessionWithDateDepotNotNull()
    {
        Set<Session> sessions = this.sessionRepository.findAll().stream().filter(session -> !session.getDate_depot().equals("")).collect(Collectors.toSet());
        log.info("Session- date depot not null : "+sessions);
        return sessions;
    }

    public Set<Project> getSessionProjects(Long sessionId)
    {
        return new HashSet<Project>(this.sessionRepository.findById(sessionId).get().getProjects());
    }

//    public Set<Criteria> getSessionCriterias(Long sessionId)
//    {
//        return new HashSet<Criteria>(this.sessionRepository.findById(sessionId).get().getCriterias());
//    }

    public Set<SessionCritere> getSessionCriteres(Long sessionId)
    {
        log.info("SessionCriteres : "+this.sessionRepository.findById(sessionId).get().getSessionCriteres());
        return  new HashSet<>(this.sessionRepository.findById(sessionId).get().getSessionCriteres());
    }

    public void addProjectToSession(Long sessionId,Long projectId)
    {
        log.info(" ************** Add Project : "+projectId+" to session : "+sessionId);
        System.out.println(" ************** Add Project : "+projectId+" to session : "+sessionId);
        Project putProject=this.projectRepository.findById(projectId).get();
        Session putSession=this.sessionRepository.findById(sessionId).get();

        putSession.getProjects().add(putProject);
        putProject.getSessions().add(putSession);

        this.projectRepository.saveAndFlush(putProject);
        this.sessionRepository.saveAndFlush(putSession);
    }

    public void setSessionProjects(Long sessionId,Set<Project> projects)
    {
        Set<Project> putProjects= new HashSet<>();
        projects.forEach(project->{
            putProjects.add(this.projectRepository.findById(project.getId()).get());
        });

        Session putSession=this.sessionRepository.findById(sessionId).get();
        putSession.setProjects(putProjects);

        putProjects.forEach(project -> {
            project.getSessions().add(putSession);
            this.projectRepository.saveAndFlush(project);
        });

        this.sessionRepository.saveAndFlush(putSession);
    }

//    public void setSessionCriterias(Long sessionId,Set<Criteria> criterias)
//    {
//        Set<Criteria> putCriterias= new HashSet<>();
//        criterias.forEach(criteria->{
//            putCriterias.add(this.criteriaRepository.findById(criteria.getId()).get());
//        });
//
//        Session putSession=this.sessionRepository.findById(sessionId).get();
//        putSession.setCriterias(putCriterias);
//
//        putCriterias.forEach(criteria -> {
//            criteria.getSessions().add(putSession);
//            this.criteriaRepository.saveAndFlush(criteria);
//        });
//
//        this.sessionRepository.saveAndFlush(putSession);
//    }

    public void setSessionCriteres(Long sessionId,Set<SessionCritere> sessionCriteres)
    {
        Set<SessionCritere> putSessionCriteres= new HashSet<>();
        sessionCriteres.forEach(sessionCritere->{
            putSessionCriteres.add(this.sessionCritereRepository.findById(sessionCritere.getId()).get());
        });

        Session putSession=this.sessionRepository.findById(sessionId).get();
        putSession.setSessionCriteres(putSessionCriteres);

        putSessionCriteres.forEach(sessionCritere -> {
            sessionCritere.setSession(putSession);
            this.sessionCritereRepository.saveAndFlush(sessionCritere);
        });

        this.sessionRepository.saveAndFlush(putSession);
    }

    public void deleteProjectFromSession(Long sessionId,Long projectId)
    {
        Project deleteProject=this.projectRepository.findById(projectId).get();
        Session putSession=this.sessionRepository.findById(sessionId).get();

        putSession.getProjects().remove(deleteProject);
        deleteProject.getSessions().remove(putSession);
        this.projectRepository.saveAndFlush(deleteProject);
        this.sessionRepository.saveAndFlush(putSession);
    }

    public void deleteSession(Long id, User user){
        Session deletedSession=this.sessionRepository.findById(id).get();
        boolean isPresent = this.sessionRepository.findById(id).isPresent();
        if(isPresent)
        {
            Set<Project> projects= deletedSession.getProjects();
            Set<SessionCritere> sessionCriteres = deletedSession.getSessionCriteres();
            Set<Run> runs = deletedSession.getRuns();

            projects.forEach(project -> {
                this.projectRepository.findById(project.getId()).get().getSessions().remove(deletedSession);
                this.projectRepository.saveAndFlush(project);
            });

            sessionCriteres.forEach(sessionCritere -> {
                this.sessionCritereRepository.deleteById(sessionCritere.getId());
            });

            projects.clear();
            sessionCriteres.clear();
            runs.clear();

            user.getSessions().remove(deletedSession);
            this.userRepository.save(user);
            deletedSession.getUsers().remove(user);

            this.sessionRepository.saveAndFlush(deletedSession);
            this.sessionRepository.deleteById(id);
        }
    }

    public void deleteAllSessions(User user)
    {
        this.sessionRepository.findAll().forEach(session -> {
            this.deleteSession(session.getId(), user);
        });
    }

    public Session getSessionById(Long sessionId) {
        return this.sessionRepository.findById(sessionId).orElse(null);
    }

    public Set<Run> getSessionRuns(Long sessionId)
    {
        Set<Run> runs = new HashSet<Run>();
        runs.addAll(this.sessionRepository.findById(sessionId).get().getRuns());
        return runs;
    }



    public void exportCSV(Long runId, HttpServletResponse response) {

        response.setContentType("text/csv");

        //create a csv writer
        Run run = this.runRepository.findById(runId).get();

        List<Project> sessionsProject = new ArrayList<Project>(this.getSessionProjects(run.getSession().getId()));
        List<SessionCritere> sessionCriteres = new ArrayList<SessionCritere>(this.getSessionCriteres(run.getSession().getId()));

        try (
                CSVPrinter csvPrinter = new CSVPrinter(response.getWriter(), CSVFormat.DEFAULT
                        .withHeader("Id", "Name","Note","Url"));
        ) {
            for (Project project : sessionsProject) {
                List<? extends Serializable> data = Arrays.asList(
                        project.getId(),
                        project.getName(),
                        project.getNote(),
                        project.getUrl()
                );
                csvPrinter.printRecord(data);
            }

            csvPrinter.printRecord("\n");
            csvPrinter.printRecord("Détails Session Criteres");
            csvPrinter.printRecord("Id","Name","Height","Seuil","Value");
            for (SessionCritere critere : sessionCriteres) {
                List<? extends Serializable> data = Arrays.asList(
                        critere.getId(),
                        critere.getName(),
                        critere.getHeight(),
                        critere.getSeuil(),
                        critere.getValue()
                );
                csvPrinter.printRecord(data);
            }

            csvPrinter.flush();
        } catch (Exception e) {
            System.out.println("Writing CSV error!");
            e.printStackTrace();
        }
    }
}
