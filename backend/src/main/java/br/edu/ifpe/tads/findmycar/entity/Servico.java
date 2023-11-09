package br.edu.ifpe.tads.findmycar.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String marca;

    private String modelo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "proposta_aceita_id", referencedColumnName = "id")
    private Proposta propostaAceita;

    @OneToMany(mappedBy = "servicoProposta", cascade = { CascadeType.ALL}, orphanRemoval = true)
    private Set<Proposta> propostas;
}
