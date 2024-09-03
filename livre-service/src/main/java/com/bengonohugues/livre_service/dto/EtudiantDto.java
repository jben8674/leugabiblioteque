package com.bengonohugues.livre_service.dto;

public class EtudiantDto {

    private Long id;
    private String programme;
    private String nom;

    // Constructeur par défaut
    public EtudiantDto() {}

    // Constructeur avec paramètres
    public EtudiantDto(Long id, String programme, String nom) {
        this.id = id;
        this.programme = programme;
        this.nom = nom;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProgramme() {
        return programme;
    }

    public void setProgramme(String programme) {
        this.programme = programme;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    // Méthode toString pour faciliter le débogage
    @Override
    public String toString() {
        return "EtudiantDto{" +
                "id=" + id +
                ", programme='" + programme + '\'' +
                ", nom='" + nom + '\'' +
                '}';
    }
}
