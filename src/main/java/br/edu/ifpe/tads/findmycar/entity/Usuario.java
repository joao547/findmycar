package br.edu.ifpe.tads.findmycar.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    private Long id;

    private String nome;

    @OneToMany(mappedBy = "avaliador")
    private Set<Avaliacao> avaliacoesFeitas;
    @OneToMany(mappedBy = "avaliado")
    private Set<Avaliacao> avaliacoesRecebidas;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Avaliacao> getAvaliacoesFeitas() {
        return avaliacoesFeitas;
    }

    public void setAvaliacoesFeitas(Set<Avaliacao> avaliacoesFeitas) {
        this.avaliacoesFeitas = avaliacoesFeitas;
    }

    public Set<Avaliacao> getAvaliacoesRecebidas() {
        return avaliacoesRecebidas;
    }

    public void setAvaliacoesRecebidas(Set<Avaliacao> avaliacoesRecebidas) {
        this.avaliacoesRecebidas = avaliacoesRecebidas;
    }
}
