package br.edu.ifpe.tads.findmycar.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "carro")
public class Carro {

    @Id
    private Long id;
    private String modelo;
    private String marca;
    private String estiloDeConducao;
    @ManyToMany(mappedBy = "carros")
    private Set<Consultor> consultores;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Set<Consultor> getConsultores() {
        return consultores;
    }

    public void setConsultores(Set<Consultor> consultores) {
        this.consultores = consultores;
    }

    public String getEstiloDeConducao() {
        return estiloDeConducao;
    }

    public void setEstiloDeConducao(String estiloDeConducao) {
        this.estiloDeConducao = estiloDeConducao;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
}
