package br.edu.ifpe.tads.findmycar.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UsuarioDto {
    private long id;

    @NotEmpty
    private String nome;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Size(min = 8, message = "Senha deve ter ao menos 8 caracteres")
    private String senha;

    private Double precoDoServico;

    private String areaDeAtuacao;

    private String disponibilidade;

    public UsuarioDto() {
    }

    public UsuarioDto(long id, String nome, String email, String senha, double precoDoServico, String areaDeAtuacao, String disponibilidade) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.precoDoServico = precoDoServico;
        this.areaDeAtuacao = areaDeAtuacao;
        this.disponibilidade = disponibilidade;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public Double getPrecoDoServico() {
        return precoDoServico;
    }

    public void setPrecoDoServico(Double precoDoServico) {
        this.precoDoServico = precoDoServico;
    }

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
}
