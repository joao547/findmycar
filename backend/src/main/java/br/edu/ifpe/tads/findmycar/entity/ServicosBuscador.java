package br.edu.ifpe.tads.findmycar.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "servicos_buscador")
public class ServicosBuscador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
