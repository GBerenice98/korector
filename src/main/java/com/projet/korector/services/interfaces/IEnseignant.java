package com.projet.korector.services.interfaces;

import com.projet.korector.entity.Enseignant;
import org.springframework.stereotype.Service;

@Service

public interface IEnseignant {

    void getAllClasse(Integer id_ense);

    boolean isEnseignantClasse(Integer idClasse);

}
