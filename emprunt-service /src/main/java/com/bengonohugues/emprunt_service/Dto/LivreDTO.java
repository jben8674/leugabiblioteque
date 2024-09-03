package com.bengonohugues.emprunt_service.Dto;

public class LivreDTO {

    private Long id;
    private String titre;
    private String auteur;
    private String isbn;
    private String programme;
    private boolean disponibilite;

    // Constructeur par défaut
    public LivreDTO() {
    }

    // Constructeur avec paramètres
    public LivreDTO(Long id, String titre, String auteur, String isbn, String programme, boolean disponibilite) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.isbn = isbn;
        this.programme = programme;
        this.disponibilite = disponibilite;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getProgramme() {
        return programme;
    }

}