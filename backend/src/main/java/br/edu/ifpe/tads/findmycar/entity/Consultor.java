package br.edu.ifpe.tads.findmycar.entity;

import jakarta.persistence.*;

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
    private Set<String> listaConsultor = new HashSet<>();

    @ElementCollection
    @CollectionTable(name="user_listSeeker")
    private Set<String> areaBuscador = new HashSet<>();

    @ManyToMany(mappedBy = "local")
    private Set<Local> areaAtuacao;

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

    public Set<String> getListaConsultor() { return listaConsultor; }

    public void setListaConsultor(Set<String> listaConsultor) { this.listaConsultor = listaConsultor; }

    public Set<String> getListaBuscador() { return areaBuscador; }

    public void setListaBuscador(Set<String> listaBuscador) { this.areaBuscador = listaBuscador; }

    public Set<Local> getLocalAtuacao() { return areaAtuacao; }

    public void setLocalAtuacao(Set<Local> localAtuacao) { this.areaAtuacao = localAtuacao; }
}
