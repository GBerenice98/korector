package com.projet.korector.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.projet.korector.entity.Project;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})

public class SessionImp {

    private Long id;
    private String name;
    private String date_depot;
    private String heureDepot;
    private List<Long> projects=new ArrayList<>();
    //private List<Long> criterias=new ArrayList<>();
    private List<Long> sessionCritere= new ArrayList<>();
    private List<Long> runs = new ArrayList<>();
    private List<Long> users = new ArrayList<>();
    private String typeSession;
/*

    public SessionImp(Long id, String name, String date_depot, String heureDepot) {
        this.id = id;
        this.name = name;
        this.date_depot = date_depot;
        this.heureDepot = heureDepot;
    } */

    public SessionImp(Long id, String name, String date_depot, String heureDepot, String typeSession) {
        this.id = id;
        this.name = name;
        this.date_depot = date_depot;
        this.heureDepot = heureDepot;
        this.typeSession = typeSession;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Long> getProjects() {
        return projects;
    }

    public void setProjects(List<Long> projects) {
        this.projects = projects;
    }

    public String getTypeSession() {
        return typeSession;
    }

    public void setTypeSession(String typeSession) {
        this.typeSession = typeSession;
    }
//    public List<Long> getCriterias() {
//        return criterias;
//    }
//
//    public void setCriterias(List<Long> criterias) {
//        this.criterias = criterias;
//    }

    public List<Long> getSessionCritere() {
        return sessionCritere;
    }

    public void setSessionCritere(List<Long> sessionCritere) {
        this.sessionCritere = sessionCritere;
    }

    public List<Long> getRuns() {
        return runs;
    }

    public void setRuns(List<Long> runs) {
        this.runs = runs;
    }

    public List<Long> getUsers() {
        return users;
    }

    public void setUsers(List<Long> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "SessionImp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date_depot='" + date_depot + '\'' +
                ", heureDepot='" + heureDepot + '\'' +
                ", projects=" + projects +
                ", sessionCritere=" + sessionCritere +
                ", runs=" + runs +
                ", users=" + users +
                '}';
    }
}
