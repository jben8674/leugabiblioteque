package com.bengonohugues.livre_service.controller;

import com.bengonohugues.livre_service.model.Livre;
import com.bengonohugues.livre_service.service.LivreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/livres")
public class LivreController {

    @Autowired
    private LivreService livreService;

    // Endpoint pour créer un nouveau livre
    @PostMapping
    public Livre creerLivre(@RequestBody Livre livre) {
        return livreService.creerLivre(livre);
    }

    // Endpoint pour récupérer un livre par son ID
    @GetMapping("/{id}")
    public Optional<Livre> obtenirLivreParId(@PathVariable Long id) {
        return livreService.obtenirLivreParId(id);
    }

    // Endpoint pour récupérer tous les livres
    @GetMapping
    public List<Livre> obtenirTousLesLivres() {
        return livreService.obtenirTousLesLivres();
    }

    // Endpoint pour supprimer un livre par son ID
    @DeleteMapping("/{id}")
    public void supprimerLivre(@PathVariable Long id) {
        livreService.supprimerLivre(id);
    }

    @GetMapping("/programme/{programme}")
    public List<Livre> getLivresByProgramme(@PathVariable String programme) {
        return livreService.getLivresByProgramme(programme);
    }


    @GetMapping("/verifier-programme")
    public boolean verifierLivreProgramme(@RequestParam Long codeEtudiant, @RequestParam Long livreId) {
        return livreService.verifierLivreProgramme(codeEtudiant, livreId);
    }
}
