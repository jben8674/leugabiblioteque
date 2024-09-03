package com.bengonohugues.etudiant_service.dto;

public class EtudiantDto {

        private Long id;
        private String nom;
        private String prenom;
        private Long codeEtudiant;
        private String programme;

        // Getter and Setter for id
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        // Getter and Setter for nom
        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        // Getter and Setter for prenom
        public String getPrenom() {
            return prenom;
        }

        public void setPrenom(String prenom) {
            this.prenom = prenom;
        }

        // Getter and Setter for etudiantId
        public Long getCodeEtudiant() {
            return codeEtudiant;
        }

        public void setCodeEtudiant(Long codeEtudiant) {
            this.codeEtudiant = codeEtudiant;
        }

        // Getter and Setter for programmeEtudes
        public String getProgramme() {
            return programme;
        }

        public void setProgramme(String programme) {
            this.programme = programme;
        }
    }


