package com.bengonohugues.livre_service.service;

import com.bengonohugues.livre_service.dto.EtudiantDto;
import com.bengonohugues.livre_service.model.Livre;
import com.bengonohugues.livre_service.repositoriy.LivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;


@Service
public class LivreService {

    @Autowired
    private LivreRepository livreRepository;


    private final RestTemplate restTemplate;

    private final String  etudiantServiceUrl = "http://localhost:5002/etudiants/ ";




    @Autowired
    public LivreService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Créer un nouveau livre
    public Livre creerLivre(Livre livre) {
        return livreRepository.save(livre);
    }

    // Récupérer un livre par son ID
    public Optional<Livre> obtenirLivreParId(Long id) {
        return livreRepository.findById(id);
    }

    // Récupérer tous les livres
    public List<Livre> obtenirTousLesLivres() {
        return livreRepository.findAll();
    }

    // Supprimer un livre par son ID
    public void supprimerLivre(Long id) {
        livreRepository.deleteById(id);
    }

    public List<Livre> getLivresByProgramme(String programme) {
        return livreRepository.findByProgramme(programme);
    }

    public boolean verifierLivreProgramme(Long codeEtudiant, Long livreId) {
        // Appel au service étudiant pour obtenir les informations sur l'étudiant
        EtudiantDto etudiant = restTemplate.getForObject(etudiantServiceUrl + codeEtudiant, EtudiantDto.class);

        // Obtenir les informations sur le livre
        Optional<Livre> livreOpt = livreRepository.findById(livreId);

        if (etudiant != null && livreOpt.isPresent()) {
            Livre livre = livreOpt.get();
            return livre.getProgramme().equalsIgnoreCase(etudiant.getProgramme());
        }
        return false;
    }
}
