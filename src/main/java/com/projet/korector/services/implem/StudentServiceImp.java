package com.projet.korector.services.implem;

import com.projet.korector.entity.Student;
import com.projet.korector.entity.User;
import com.projet.korector.repository.StudentRepository;
import com.projet.korector.repository.UserRepository;
import com.projet.korector.services.interfaces.IStudent;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StudentServiceImp implements  IStudent {

    @Autowired
    private StudentRepository userDAO;

    @Override
    public List<Student> getAllStudent(String idClasse) {

        return null;
    }

    @Override
    public Integer getIdClasse(Student s) {
        return null;
    }

    @Override
    public void updateidClasse(Student s) {

    }

    @Override
    public void updateClasseForAllStudent(Integer idClasse) {

    }

}
