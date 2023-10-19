package br.edu.ifpe.tads.findmycar.dto;

import br.edu.ifpe.tads.findmycar.annotations.Conditional;
import br.edu.ifpe.tads.findmycar.enums.TipoUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Conditional(selected = "tipo", values = {"consultor"}, required = {"precoDoServico", "areaDeAtuacao", "disponibilidade"})
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

    @NotNull
    private TipoUsuario tipo;

    private Double precoDoServico;

    private String areaDeAtuacao;

    private String disponibilidade;

    public UsuarioDto() {
    }

    public UsuarioDto(long id, String nome, String email, String senha, TipoUsuario tipo, double precoDoServico, String areaDeAtuacao, String disponibilidade) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
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

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }
}
