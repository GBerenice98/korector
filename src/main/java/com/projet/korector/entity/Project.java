package com.projet.korector.entity;

import com.projet.korector.model.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "projects", uniqueConstraints = {
        @UniqueConstraint(columnNames = "url")
}
)
public class Project implements Serializable {

    private static final long serialVersionUID = -2054386655979281969L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private String url;
    private Float note;
    private String dateDepot;
    @ManyToMany(mappedBy = "projects")
    private Set<Session> sessions;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinTable(name="user_projects",
            joinColumns={@JoinColumn(name="project_id", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="user_id", referencedColumnName="id")})
    private User user;

    public Project() {

    }

    public Project(Long id, String name, String description, String url, String date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.sessions= new HashSet<>();
        this.dateDepot = date;
    }

    public Project(String name, String description, String url, String date) {
        this.name = name;
        this.description = description;
        this.url = url;
        this.sessions= new HashSet<>();
        this.dateDepot = date;
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

    public String getDateDepot() {
        return dateDepot;
    }

    public void setDateDepot(String dateDepot) {
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


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
