package com.projet.korector.services.implem;

import com.projet.korector.repository.StudentRepository;
import com.projet.korector.services.interfaces.IEnseignant;
import org.springframework.beans.factory.annotation.Autowired;

public class EnseignantServiceImp implements IEnseignant {

    @Autowired
    private StudentRepository userDAO;


    @Override
    public void getAllClasse(Integer id_ense) {

    }

    @Override
    public boolean isEnseignantClasse(Integer idClasse) {
        return false;
    }
}
