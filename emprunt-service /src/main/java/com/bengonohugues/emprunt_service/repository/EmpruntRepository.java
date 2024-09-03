package com.bengonohugues.emprunt_service.repository;

import com.bengonohugues.emprunt_service.model.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmpruntRepository extends JpaRepository <Emprunt,Long> {


    List<Emprunt> findByCodeEtudiant(Long codeEtudiant);


    List<Emprunt> findByDateRetourPrevueBefore(LocalDate date);
}