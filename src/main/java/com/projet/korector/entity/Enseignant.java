package com.projet.korector.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(	name = "Enseignant"

)
public class Enseignant extends User {

    public Enseignant(String userName,String firstName,String lastName, String email, String password){

        super (userName, firstName,lastName,email,password);

    }

}

