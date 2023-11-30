package br.edu.ifpe.tads.findmycar.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TP_USUARIO", discriminatorType = DiscriminatorType.STRING, length = 20)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    @Column(unique = true)
    private String email;
    private String senha;

    @OneToMany(mappedBy = "avaliador")
    private Set<Avaliacao> avaliacoesFeitas;
    @OneToMany(mappedBy = "avaliado")
    private Set<Avaliacao> avaliacoesRecebidas;
    @OneToMany(mappedBy = "solicitante")
    private Set<Servico> servicosSolicitados;

    @OneToMany(mappedBy = "proponente")
    private Set<Proposta> propostasEnviadas;

    @OneToMany(mappedBy = "destinatario")
    private Set<Proposta> propostasRecebidas;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<Servico> getServicosSolicitados() {
        return servicosSolicitados;
    }

    public void setServicosSolicitados(Set<Servico> servicosSolicitados) {
        this.servicosSolicitados = servicosSolicitados;
    }

    public Set<Proposta> getPropostasEnviadas() {
        return propostasEnviadas;
    }

    public void setPropostasEnviadas(Set<Proposta> propostasEnviadas) {
        this.propostasEnviadas = propostasEnviadas;
    }

    public Set<Proposta> getPropostasRecebidas() {
        return propostasRecebidas;
    }

    public void setPropostasRecebidas(Set<Proposta> propostasRecebidas) {
        this.propostasRecebidas = propostasRecebidas;
    }
}
