package br.edu.ifpe.tads.findmycar.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@DiscriminatorValue("Consultor")
public class Consultor extends Usuario{
    private double precoDoServico;
    private String areaDeAtuacao;
    private String disponibilidade;
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
}
