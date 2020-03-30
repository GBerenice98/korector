package com.projet.korector.repository;
import com.projet.korector.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SectionRepository extends JpaRepository<Section,Long> {
    Section getSectionByName(Long name);

    Section findSectionById(Long id);
}
