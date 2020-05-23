package com.projet.korector.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="session")
public class Session implements Serializable {

    private static final long serialVersionUID = -2054386655979281969L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String date_depot;
    private String heureDepot;
private String typeSession;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name="sessions_projects",
            joinColumns = {
                    @JoinColumn(name = "session_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "project_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    private Set<Project> projects = new HashSet<>();

//    @JsonIgnore
//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
//    @JoinTable(name="sessions_criterias",
//            joinColumns = {
//                    @JoinColumn(name = "session_id",
//                            nullable = false, updatable = false)},
//            inverseJoinColumns = {
//                    @JoinColumn(name = "criteria_id",
//                            nullable = false, updatable = false)})
//    private Set<Criteria> criterias = new HashSet<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "sessions",fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Set<User> users = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "session", fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST)
    private Set<Run> runs = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "session", fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST)
    private Set<SessionCritere> sessionCriteres = new HashSet<>();

    @JsonIgnore

    /*
    @OneToMany(mappedBy = "resultsSonarSessions",fetch = FetchType.LAZY,  cascade = CascadeType.PERSIST)
    private Set <SonarResults> results  = new HashSet<>();
*/


    public Session() { }

    public Session(Long id) {
        this.id = id;
    }

    public Session(String n, String date, String heure) {
        this.name = n;
        this.date_depot=date;
        this.heureDepot=heure;
    }

    public Session(Long id, String n, String date, String heure) {
        this.id=id;
        this.name = n;
        this.date_depot=date;
        this.heureDepot=heure;
    }

    public Session(String name, String date_depot, String heureDepot, String typeSession) {
        this.name = name;
        this.date_depot = date_depot;
        this.heureDepot = heureDepot;
        this.typeSession = typeSession;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate_depot() {
        return date_depot;
    }

    public void setDate_depot(String date_depot) {
        this.date_depot = date_depot;
    }

    public String getHeureDepot() {
        return heureDepot;
    }

    public void setHeureDepot(String heureDepot) {
        this.heureDepot = heureDepot;
    }

    public String getTypeSession() {
        return typeSession;
    }

    public void setTypeSession(String typeSession) {
        this.typeSession = typeSession;
    }

    @JsonIgnore
    public Set<Project> getProjects() {
        return projects;
    }

    @JsonIgnore
    public void setProjects(Set<Project> projects) {
        this.projects= projects;
    }

//    @JsonIgnore
//    public Set<Criteria> getCriterias() {
//        return criterias;
//    }
//
//    @JsonIgnore
//    public void setCriterias(Set<Criteria> criterias) {
//        this.criterias = criterias;
//    }

    public Set<Run> getRuns() {
        return runs;
    }

    public void setRuns(Set<Run> runs) {
        this.runs = runs;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @JsonIgnore
    public Set<SessionCritere> getSessionCriteres() {
        return sessionCriteres;
    }

    @JsonIgnore
    public void setSessionCriteres(Set<SessionCritere> sessionCriteres) {
        this.sessionCriteres = sessionCriteres;
    }

    /*
    public Set <SonarResults> getResults() {
        return results;
    }

    public void setResults(Set <SonarResults>  results) {
        this.results = results;
    } */

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date_depot='" + date_depot + '\'' +
                ", heureDepot='" + heureDepot + '\'' +
                ", projects=" + projects +
                ", type=" + typeSession +

                ", sessionriteres=" + sessionCriteres +
                ", runs=" + runs +
                '}';
    }
}
