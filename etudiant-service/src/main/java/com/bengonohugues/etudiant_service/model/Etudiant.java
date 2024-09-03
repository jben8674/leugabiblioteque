package com.bengonohugues.etudiant_service.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "etudiant")
public class Etudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false, unique = true)
    private Long codeEtudiant;

    @Column(nullable = false)
    private String programme;






    // Constructeurs
    public Etudiant() {
    }

    public Etudiant(String nom, String prenom, Long codeEtudiant, String programme) {
        this.nom = nom;
        this.prenom = prenom;
        this.codeEtudiant = codeEtudiant;
        this.programme = programme;

    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Long getCodeEtudiant() {
        return codeEtudiant;
    }

    public void setCodeEtudiant(Long codeEtudiant) {
        this.codeEtudiant = codeEtudiant;
    }

    public String getProgramme() {
        return programme;
    }

    public void setProgramme(String programme) {
        this.programme = programme;
    }






    // Méthode toString pour l'affichage
    @Override
    public String toString() {
        return "Etudiant{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", codeEtudiant='" + codeEtudiant + '\'' +
                ", programme='" + programme + '\'' +

                '}';
    }
}
