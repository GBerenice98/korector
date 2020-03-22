package miage.korector.login.services.interfaces;

import miage.korector.login.models.Student;

import java.util.List;

public interface IStudent {

    <List> Student getAllUserByIdClasse(Integer Id_Classe);
    Integer getIdClasse(Student u);
    void updateidClasse(Student u);

    // Change la classe pour une liste d'utilisateurs
    List<Student> updateClasseForAllUser(Integer idClasse);
}
