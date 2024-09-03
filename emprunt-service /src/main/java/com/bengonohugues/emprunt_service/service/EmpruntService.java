package com.bengonohugues.emprunt_service.service;

import com.bengonohugues.emprunt_service.Dto.EmpruntDTO;
import com.bengonohugues.emprunt_service.Dto.EtudiantDTO;
import com.bengonohugues.emprunt_service.Dto.LivreDTO;
import com.bengonohugues.emprunt_service.Exception.LivreNonDisponibleException;
import com.bengonohugues.emprunt_service.model.Emprunt;
import com.bengonohugues.emprunt_service.repository.EmpruntRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmpruntService {

    @Autowired
    private EmpruntRepository empruntRepository;


    private final RestTemplate restTemplate;

    private final String  etudiantServiceUrl = "http://localhost:5002/etudiants/ ";
    private final String  livreServiceUrl = "http://localhost:5007/livres/ ";



    @Autowired
    public EmpruntService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Créer un nouvel emprunt
    public EmpruntDTO creerEmprunt(EmpruntDTO empruntDTO) {
        if (!verifierEligibiliteEmprunt(empruntDTO.getCodeEtudiant(), empruntDTO.getLivreId())) {
            throw new LivreNonDisponibleException("Student not find");
        }
        Emprunt emprunt = new Emprunt();
        emprunt.setCodeEtudiant(empruntDTO.getCodeEtudiant());
        emprunt.setLivreId(empruntDTO.getLivreId());
        emprunt.setDateEmprunt(LocalDate.now());
        emprunt.setDateRetourPrevue(LocalDate.now().plusDays(14));
        emprunt.setDateRetourEffective(null); // Pas encore retourné

        Emprunt saveEmprunt= empruntRepository.save(emprunt);
        return convertToDTO(saveEmprunt);
    }

    public boolean verifierEligibiliteEmprunt(Long codeEtudiant, Long livreId) {
        // Récupérer les informations sur l'étudiant
        EtudiantDTO etudiant = restTemplate.getForObject(etudiantServiceUrl +codeEtudiant, EtudiantDTO.class);

        // Récupérer les informations sur le livre
        LivreDTO livre = restTemplate.getForObject(livreServiceUrl + livreId, LivreDTO.class);

        if (etudiant != null && livre != null) {
            return livre.getProgramme().equalsIgnoreCase(etudiant.getProgramme());
        }
        return false;
    }


    // Récupérer un emprunt par son ID
    public Optional<Emprunt> obtenirEmpruntParId(Long id) {
        return empruntRepository.findById(id);
    }

    // Récupérer tous les emprunts
    public List<Emprunt> obtenirTousLesEmprunts() {
        return empruntRepository.findAll();
    }

    // Mettre à jour la date de retour effective lorsque le livre est retourné
    public Emprunt mettreAJourDateRetour(Long empruntId, LocalDate dateRetourEffective) {
        Optional<Emprunt> empruntOpt = empruntRepository.findById(empruntId);
        if (empruntOpt.isPresent()) {
            Emprunt emprunt = empruntOpt.get();
            emprunt.setDateRetourEffective(dateRetourEffective);
            return empruntRepository.save(emprunt);
        } else {
            throw new RuntimeException("Emprunt non trouvé pour l'ID : " + empruntId);
        }
    }

    // Supprimer un emprunt par son ID
    public void supprimerEmprunt(Long id) {
        empruntRepository.deleteById(id);
    }

    private EmpruntDTO convertToDTO(Emprunt emprunt) {
        EmpruntDTO dto = new EmpruntDTO();
        dto.setId(emprunt.getId());
        dto.setCodeEtudiant(emprunt.getCodeEtudiant());
        dto.setLivreId(emprunt.getLivreId());
        dto.setDateEmprunt(emprunt.getDateEmprunt());
        dto.setDateRetourPrevue(emprunt.getDateRetourPrevue());
        dto.setDateRetourEffective(emprunt.getDateRetourEffective());
        return dto;
    }

    public List<EmpruntDTO> obtenirEmpruntsParEtudiant(Long codeEtudiant) {
        List<Emprunt> emprunts = empruntRepository.findByCodeEtudiant(codeEtudiant);
        return emprunts.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public EmpruntDTO retournerLivre(Long id) {
        Optional<Emprunt> optionalEmprunt = empruntRepository.findById(id);
        if (optionalEmprunt.isPresent()) {
            Emprunt emprunt = optionalEmprunt.get();
            emprunt.setDateRetourEffective(LocalDate.now());
            Emprunt updatedEmprunt = empruntRepository.save(emprunt);
            return convertToDTO(updatedEmprunt);
        }
        // Retourne null ou une autre valeur par défaut si l'emprunt n'est pas trouvé
        return null;
    }

    public List<EmpruntDTO> obtenirEmpruntsEnRetard() {
        LocalDate now = LocalDate.now();
        List<Emprunt> empruntsEnRetard = empruntRepository.findByDateRetourPrevueBefore(now);
        return empruntsEnRetard.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


}
