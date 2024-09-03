package com.bengonohugues.emprunt_service.controller;

import com.bengonohugues.emprunt_service.Dto.EmpruntDTO;
import com.bengonohugues.emprunt_service.model.Emprunt;
import com.bengonohugues.emprunt_service.service.EmpruntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/emprunts")
public class EmpruntController {

    @Autowired
    private EmpruntService empruntService;

    // Endpoint pour créer un nouvel emprunt
    @PostMapping
    public EmpruntDTO creerEmprunt(@RequestBody EmpruntDTO empruntDTO) {
        return empruntService.creerEmprunt(empruntDTO);
    }

    // Endpoint pour récupérer un emprunt par son ID
    @GetMapping("/{id}")
    public Optional<Emprunt> obtenirEmpruntParId(@PathVariable Long id) {
        return empruntService.obtenirEmpruntParId(id);
    }

    // Endpoint pour récupérer tous les emprunts
    @GetMapping
    public List<Emprunt> obtenirTousLesEmprunts() {
        return empruntService.obtenirTousLesEmprunts();
    }

    // Endpoint pour mettre à jour la date de retour effective d'un emprunt
    @PutMapping("/{id}/retour/date")
    public Emprunt mettreAJourDateRetour(@PathVariable Long id,
                                         @RequestParam LocalDate dateRetourEffective) {
        return empruntService.mettreAJourDateRetour(id, dateRetourEffective);
    }

    // Endpoint pour retourner un livre
    @PutMapping("/{id}/retour")
    public EmpruntDTO retournerLivre(@PathVariable Long id) {
        return empruntService.retournerLivre(id);
    }

    // Endpoint pour supprimer un emprunt par son ID
    @DeleteMapping("/{id}")
    public void supprimerEmprunt(@PathVariable Long id) {
        empruntService.supprimerEmprunt(id);
    }

    @GetMapping("/verifier")
    public Boolean verifierEligibiliteEmprunt(
            @RequestParam Long codeEtudiant,
            @RequestParam Long livreId) {
        return empruntService.verifierEligibiliteEmprunt(codeEtudiant, livreId);
    }

    @GetMapping("/etudiant/{codeEtudiant}")
    public List<EmpruntDTO> obtenirEmpruntsParEtudiant(@PathVariable Long codeEtudiant) {
        return empruntService.obtenirEmpruntsParEtudiant(codeEtudiant);
    }

    @GetMapping("/en-retard")
    public List<EmpruntDTO> obtenirEmpruntsEnRetard() {
        return empruntService.obtenirEmpruntsEnRetard();
    }
}
