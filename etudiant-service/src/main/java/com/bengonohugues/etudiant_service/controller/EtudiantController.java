package com.bengonohugues.etudiant_service.controller;

import com.bengonohugues.etudiant_service.dto.EtudiantDto;
import com.bengonohugues.etudiant_service.model.Etudiant;

import java.util.List;


import com.bengonohugues.etudiant_service.service.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/etudiants")
public class EtudiantController {

    @Autowired
    private final EtudiantService etudiantService;

    public EtudiantController(EtudiantService etudiantService) {
        this.etudiantService = etudiantService;
    }

    @PostMapping
    public String enregistrerEtudiant(@RequestBody EtudiantDto etudiantDTO) {
        String savedEtudiant = String.valueOf(etudiantService.enregistrerEtudiant(etudiantDTO));
        return savedEtudiant;
    }

    @PutMapping("/{id}")
    public ResponseEntity<EtudiantDto> mettreAJourEtudiant(@PathVariable Long id, @RequestBody EtudiantDto etudiantDto) {
        EtudiantDto updatedEtudiant = etudiantService.mettreAJourEtudiant(id, etudiantDto);
        return new ResponseEntity<>(updatedEtudiant, HttpStatus.OK);
    }

    @GetMapping("/{codeEtudiant}")
    public ResponseEntity<EtudiantDto> obtenirEtudiantParCode(@PathVariable Long codeEtudiant) {
        EtudiantDto etudiantDTO = etudiantService.obtenirEtudiantParCode(codeEtudiant);
        return new ResponseEntity<>(etudiantDTO, HttpStatus.OK);
    }

    // Endpoint pour récupérer tous les étudiants
    @GetMapping
    public List<Etudiant> recupererTousLesEtudiants() {
        return etudiantService.recupererTousLesEtudiants();
    }
}