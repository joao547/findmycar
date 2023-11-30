package br.edu.ifpe.tads.findmycar.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private String marca;

    private String modelo;

    @ManyToOne
    @JoinColumn(name = "solicitante_id", nullable = false)
    private Usuario solicitante;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "proposta_aceita_id", referencedColumnName = "id")
    private Proposta propostaAceita;

    @OneToMany(mappedBy = "servicoProposta", cascade = { CascadeType.ALL}, orphanRemoval = true)
    private Set<Proposta> propostas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Usuario getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Usuario solicitante) {
        this.solicitante = solicitante;
    }

    public Proposta getPropostaAceita() {
        return propostaAceita;
    }

    public void setPropostaAceita(Proposta propostaAceita) {
        this.propostaAceita = propostaAceita;
    }

    public Set<Proposta> getPropostas() {
        return propostas;
    }

    public void setPropostas(Set<Proposta> propostas) {
        this.propostas = propostas;
    }
}
