package com.projet.korector.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "Criteria" , uniqueConstraints = {@UniqueConstraint(columnNames ="name")})
public class Criteria implements Serializable {

    private static final long serialVersionUID = -2054386655979281969L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name ="name")
    private String name;

    @Column(name="Type")
    private String type;

    @Column(name="url")
    private String url;


    public Criteria( String name, String type, String url) {
        this.name = name;
        this.type = type;
        this.url = url;
    }

    public Criteria() {

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

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Criteria{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
