package com.projet.korector.repository;

import com.projet.korector.entity.Enseignant;
import com.projet.korector.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnseignantRepository extends JpaRepository<Enseignant,Long> {
}
