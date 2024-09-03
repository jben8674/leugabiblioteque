package com.bengonohugues.emprunt_service.Dto;

public class EtudiantDTO {

    private Long id;
    private String name;
    private String programme;

    // Constructeur par défaut
    public EtudiantDTO() {}

    // Constructeur avec paramètres
    public EtudiantDTO(Long id, String name, String programme) {
        this.id = id;
        this.name = name;
        this.programme = programme;
    }

    // Getters et Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProgramme() {
        return programme;
    }

    public void setProgramme(String programme) {
        this.programme = programme;
    }


}
