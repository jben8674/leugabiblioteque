package com.bengonohugues.etudiant_service.repository;


import com.bengonohugues.etudiant_service.model.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {

    // Méthode pour trouver un étudiant par son code étudiant
    Optional<Etudiant> findByCodeEtudiant(Long codeEtudiant);

}