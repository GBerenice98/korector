package com.projet.korector.entity;

import com.projet.korector.model.User;
import org.springframework.boot.autoconfigure.web.ServerProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Project implements Serializable {

    private static final long serialVersionUID = -2054386655979281969L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Float note;
    private Date dateDepot;
    @ManyToMany(mappedBy = "projects")
    private Set<Session> sessions;

    public Project() {

    }

    public Project(Long id, String name) {
        this.id = id;
        this.name = name;
        this.sessions= new HashSet<>();
        this.dateDepot = new Date();
    }

    public Project(String name, Float note) {
        this.name = name;
        this.note = note;
        this.sessions= new HashSet<>();
        this.dateDepot = new Date();
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

    public Float getNote() {
        return note;
    }

    public void setNote(Float note) {
        this.note = note;
    }

    public Set<Session> getSessions() {
        return sessions;
    }

    public void setSessions(Set<Session> sessions) {
        this.sessions = sessions;
    }

    public Date getDateDepot() {
        return dateDepot;
    }

    public void setDateDepot(Date dateDepot) {
        this.dateDepot = dateDepot;
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Project{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", note=").append(note);
        sb.append(", sessions=").append(sessions);
        sb.append('}');
        return sb.toString();
    }



}
