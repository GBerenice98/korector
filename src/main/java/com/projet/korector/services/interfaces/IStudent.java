package com.projet.korector.services.interfaces;

import com.projet.korector.entity.Student;

import java.util.List;

public interface IStudent {
    List<Student> getAllStudent(String idClasse);

    Integer getIdClasse(Student s);
    void updateidClasse(Student s);

    // Change la classe pour une liste d'utilisateurs
    void updateClasseForAllStudent(Integer idClasse);
}
