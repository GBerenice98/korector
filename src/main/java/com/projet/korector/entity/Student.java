package com.projet.korector.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Table(	name = "Etudiant",
        uniqueConstraints = {

                @UniqueConstraint(columnNames = "github")

        })
public class Student extends User {

    @NotBlank
    @Column(name = "github")

    @Size(max = 50)
    private String  github ;

    @NotBlank
    @Column(name = "id_Classe")

    @Size(max = 50)
    private String id_Classe;

    public Student(String userName,String firstName,String lastName, String email, String password, String github, String id_Classe) {
        super (userName, firstName,lastName,email,password);

        this.github = github;
        this.id_Classe = id_Classe;
    }

    public String getGithub() {
        return github;
    }


    public String getId_Classe() {
        return id_Classe;
    }



    public void setGithub(String github) {
        this.github = github;
    }



    public void setId_Classe(String id_Classe) {
        id_Classe = id_Classe;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Session{");

        sb.append(", github=").append(github);
        sb.append(", idClasse=").append(id_Classe);

        sb.append('}');
        return sb.toString();
    }


}
