package com.projet.korector.repository;

import com.projet.korector.entity.Session;
import com.projet.korector.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
