package br.edu.ifpe.tads.findmycar.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.Set;

@Entity
@DiscriminatorValue("Cliente")
public class Cliente extends Usuario{
    @OneToMany(mappedBy = "cliente")
    private Set<Consulta> consultas;
    @OneToMany(mappedBy = "cliente")
    private Set<Proposta> propostas;

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
}
