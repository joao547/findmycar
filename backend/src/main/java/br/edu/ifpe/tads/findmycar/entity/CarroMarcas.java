package br.edu.ifpe.tads.findmycar.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "carro_marcas")
public class CarroMarcas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    public String getNome() {
        return nome;
    }

    public Long getId() {
        return id;
    }
}
