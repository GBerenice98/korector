package com.projet.korector.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table( name ="results_sonars")
public class SonarResults {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column

    private String bugs;
    @Column

    private String vuls;
    @Column

    private String debt;
    @Column

    private String smells;
   /* @Column

    private String coverage; */
    @Column

    private String dups;

    @Column
    private String dups_block;

    @Column
    private LocalDateTime date;

    @Column
    private  Long projectId;

    @Column
    private  Double note_finale;

    @Column
    private  Long sessionId;

    ;/*
    @JsonIgnore

    @OneToOne(fetch = FetchType.LAZY)
    @JoinTable(name="resultsSonars_projects",
            joinColumns = {
                    @JoinColumn(name = "resultsSonar_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "project_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    private Project resultsSonarProjects = new Project();


    @JsonIgnore

    @OneToOne(fetch = FetchType.LAZY)
    @JoinTable(name="resultsSonars_sessions",
            joinColumns = {
                    @JoinColumn(name = "resultsSonar_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "session_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    private Session resultsSonarSessions = new Session(); */

    public SonarResults(){

    }

    public SonarResults( String bugs, String vuls, String debt, String smells,  String dups, String dups_block,Long project_id,Long session_id,Double note_finale, LocalDateTime date) {
        this.bugs = bugs;
        this.vuls = vuls;
        this.debt = debt;
        this.smells = smells;
      //  this.coverage = coverage;
        this.dups = dups;
        this.dups_block = dups_block;
        this.date = date;
        this.projectId = project_id;
        this.note_finale = note_finale;
        this.sessionId = session_id;
    }

/*
    public SonarResults(Long id, String bugs, String vuls, String debt, String smells, String coverage, String dups, String dups_block,Long project_id,  Long session_id,Double note_finale, LocalDateTime  date) {
        this.id = id;
        this.bugs = bugs;
        this.vuls = vuls;
        this.debt = debt;
        this.smells = smells;
        this.coverage = coverage;
        this.dups = dups;
        this.dups_block = dups_block;
        this.date = date;
        this.project_id = project_id;
        this.note_finale = note_finale;
        this.session_id = session_id;
    } */


    public String getBugs() {
        return bugs;
    }

    public void setBugs(String bugs) {
        this.bugs = bugs;
    }

   /* public String getCoverage() {
        return coverage;
    }

    public void setCoverage(String coverage) {
        this.coverage = coverage;
    } */

    public String getDebt() {
        return debt;
    }

    public void setDebt(String debt) {
        this.debt = debt;
    }

    public String getDups_block() {
        return dups_block;
    }

    public void setDups_block(String dups_block) {
        this.dups_block = dups_block;
    }

    public String getDups() {
        return dups;
    }

    public void setDups(String dups) {
        this.dups = dups;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getSmells() {
        return smells;
    }

    public void setSmells(String smells) {
        this.smells = smells;
    }

    public String getVuls() {
        return vuls;
    }

    public void setVuls(String vuls) {
        this.vuls = vuls;
    }



    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }


    public Double getNote_finale() {
        return note_finale;
    }

    public void setNote_finale(Double note_finale) {
        this.note_finale = note_finale;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }


    @Override
    public String toString() {
        return "SonarResults{" +
                "id=" + id +
                ", bugs=" + bugs +
                ", vuls=" + vuls +
                ", debt=" + debt +
                ", smells=" + smells +
                ", dups=" + dups +
                ", dupBlock=" + dups_block +
                '}';
    }
}
