package br.edu.ifpe.tads.findmycar.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("Consultor")
public class Consultor extends Usuario{
    private double precoDoServico;
    private String areaDeAtuacao;
    private String disponibilidade;

    @ElementCollection
    @CollectionTable(name="user_listConsultant")
    private Set<String> areasConsultor = new HashSet<>();

    @ElementCollection
    @CollectionTable(name="user_listSeeker")
    private Set<String> areasBuscador = new HashSet<>();

    @ManyToMany
    @Cascade(CascadeType.ALL)
    @JoinTable(
        name = "consultor_locais",
        joinColumns = @JoinColumn(name = "consultor_id"),
        inverseJoinColumns = @JoinColumn(name = "local_id")
    )
    private Set<Local> locais;

    @OneToMany(mappedBy = "consultor")
    private Set<Consulta> consultas;
    @OneToMany(mappedBy = "consultor")
    private Set<Proposta> propostas;
    @ManyToMany
    @JoinTable(
            name = "consultor_carro",
            joinColumns = @JoinColumn(name = "consultor_id"),
            inverseJoinColumns = @JoinColumn(name = "carro_id"))
    private Set<Carro> carros;

    public String getAreaDeAtuacao() {
        return areaDeAtuacao;
    }

    public void setAreaDeAtuacao(String areaDeAtuacao) {
        this.areaDeAtuacao = areaDeAtuacao;
    }

    public String getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(String disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public double getPrecoDoServico() {
        return precoDoServico;
    }

    public void setPrecoDoServico(double precoDoServico) {
        this.precoDoServico = precoDoServico;
    }

    public Set<Carro> getCarros() {
        return carros;
    }

    public void setCarros(Set<Carro> carros) {
        this.carros = carros;
    }

    public Set<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(Set<Consulta> consultas) {
        this.consultas = consultas;
    }

    public Set<Proposta> getPropostas() {
        return propostas;
    }

    public void setPropostas(Set<Proposta> propostas) {
        this.propostas = propostas;
    }

    public Set<String> getAreasConsultor() { return areasConsultor; }

    public void setAreasConsultor(Set<String> areasConsultor) { this.areasConsultor = areasConsultor; }

    public Set<String> getAreasBuscador() { return areasBuscador; }

    public void setAreasBuscador(Set<String> areaBuscador) { this.areasBuscador = areaBuscador; }

    public Set<Local> getLocais() { return locais; }

    public void setLocais(Set<Local> locais) { this.locais = locais; }
}
