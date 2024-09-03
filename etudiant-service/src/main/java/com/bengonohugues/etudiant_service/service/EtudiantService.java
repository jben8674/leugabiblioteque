package com.bengonohugues.etudiant_service.service;

import com.bengonohugues.etudiant_service.dto.EtudiantDto;
import com.bengonohugues.etudiant_service.exception.EtudiantAlreadyExistExeption;
import com.bengonohugues.etudiant_service.exception.EtudiantNotFoundException;
import com.bengonohugues.etudiant_service.model.Etudiant;
import com.bengonohugues.etudiant_service.repository.EtudiantRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class EtudiantService {

    @Autowired
    private final EtudiantRepository etudiantRepository;

    public EtudiantService(EtudiantRepository etudiantRepository) {
        this.etudiantRepository = etudiantRepository;
    }

    @Transactional
    public EtudiantDto enregistrerEtudiant(EtudiantDto etudiantDTO) {
         Optional<Etudiant > existingEtudiant = etudiantRepository.findByCodeEtudiant(etudiantDTO.getCodeEtudiant());

         if(existingEtudiant.isPresent()){
             throw new EtudiantAlreadyExistExeption("etudiant deja existant avec ce code");
         }

        Etudiant etudiant = new Etudiant();
        etudiant.setNom(etudiantDTO.getNom());
        etudiant.setPrenom(etudiantDTO.getPrenom());
        etudiant.setCodeEtudiant(etudiantDTO.getCodeEtudiant());
        etudiant.setProgramme(etudiantDTO.getProgramme());

        Etudiant savedEtudiant = etudiantRepository.save(etudiant);
        return convertToDTO(savedEtudiant);
    }

    @Transactional
    public EtudiantDto mettreAJourEtudiant(Long id, EtudiantDto etudiantDTO) {
        Etudiant etudiant = etudiantRepository.findById(id)
                .orElseThrow(() -> new EtudiantNotFoundException("Étudiant non trouvé avec l'id: " + id));

        etudiant.setNom(etudiantDTO.getNom());
        etudiant.setPrenom(etudiantDTO.getPrenom());
        etudiant.setCodeEtudiant(etudiantDTO.getCodeEtudiant());
        etudiant.setProgramme(etudiantDTO.getProgramme());

        Etudiant updatedEtudiant = etudiantRepository.save(etudiant);
        return convertToDTO(updatedEtudiant);
    }

    public EtudiantDto obtenirEtudiantParCode(Long codeEtudiant) {
        Etudiant etudiant = etudiantRepository.findByCodeEtudiant(codeEtudiant)
                .orElseThrow(() -> new EtudiantNotFoundException("Étudiant non trouvé avec le code: " + codeEtudiant));
        return convertToDTO(etudiant);
    }

    private EtudiantDto convertToDTO(Etudiant etudiant) {
        EtudiantDto dto = new EtudiantDto();
        dto.setId(etudiant.getId());
        dto.setNom(etudiant.getNom());
        dto.setPrenom(etudiant.getPrenom());
        dto.setCodeEtudiant(etudiant.getCodeEtudiant());
        dto.setProgramme(etudiant.getProgramme());
        return dto;
    }



    public List<Etudiant >recupererTousLesEtudiants(){
        return etudiantRepository.findAll();
    }
}