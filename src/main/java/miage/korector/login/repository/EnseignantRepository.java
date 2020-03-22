package miage.korector.login.repository;

import miage.korector.login.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnseignantRepository extends JpaRepository<Student,Long> {
}
