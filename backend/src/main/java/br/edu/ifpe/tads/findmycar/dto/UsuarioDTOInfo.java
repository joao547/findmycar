package br.edu.ifpe.tads.findmycar.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioDTOInfo {
    private final Long id;
    private final String nome;
    private final String email;
    private final Double precoServico;
    private final String areaAtuacao;
    private final String tipo;

    public UsuarioDTOInfo(Long id, String nome, String email, Double precoServico, String areaAtuacao, String tipo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.precoServico = precoServico;
        this.areaAtuacao = areaAtuacao;
        this.tipo = tipo;
    }

    public UsuarioDTOInfo(Long id, String nome, String email, String tipo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.precoServico = null;
        this.areaAtuacao = null;
        this.tipo = tipo;
    }
    public Long getId() {
        return this.id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public Double getPrecoServico() {
        return precoServico;
    }

    public String getAreaAtuacao() {
        return areaAtuacao;
    }

    public String getTipo() {
        return tipo;
    }
}
