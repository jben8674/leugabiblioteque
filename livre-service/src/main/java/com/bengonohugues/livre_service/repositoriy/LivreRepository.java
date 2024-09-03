package com.bengonohugues.livre_service.repositoriy;


import com.bengonohugues.livre_service.model.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivreRepository extends JpaRepository<Livre, Long> {
    // Vous pouvez ajouter des méthodes personnalisées ici si nécessaire


    List<Livre> findByProgramme(String programme);

}


