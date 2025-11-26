package com.demo.spring.programacion.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;


@Entity
public class Habito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;

    @Enumerated(EnumType.STRING)
    private NivelDeImportanciaEnum nivelDeImportanciaEnum;

    @ManyToMany(mappedBy = "habitos")
    private List<EntradaDiaria> entradas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public NivelDeImportanciaEnum getNivelDeImportanciaEnum() {
        return nivelDeImportanciaEnum;
    }

    public void setNivelDeImportanciaEnum(NivelDeImportanciaEnum nivelDeImportanciaEnum) {
        this.nivelDeImportanciaEnum = nivelDeImportanciaEnum;
    }

    public List<EntradaDiaria> getEntradas() {
        return entradas;
    }

    public void setEntradas(List<EntradaDiaria> entradas) {
        this.entradas = entradas;
    }
}