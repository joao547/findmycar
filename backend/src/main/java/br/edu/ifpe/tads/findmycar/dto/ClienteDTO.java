package br.edu.ifpe.tads.findmycar.dto;

import br.edu.ifpe.tads.findmycar.entity.CarroMarcas;
import br.edu.ifpe.tads.findmycar.entity.Local;
import br.edu.ifpe.tads.findmycar.entity.ServicosBuscador;

import java.util.Set;

public class ClienteDTO {

    private final Long idCliente;
    private final String nome;
    private final String email;
    private final String fotoBase64;
    private final Double valorFechado;
    private String tipoServico;
    private String servicoContratado;

    public ClienteDTO(Long idCliente, String nome, String email, String fotoBase64, Double valorFechado, String tipoServico, String servicoContratado) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.email = email;
        this.fotoBase64 = fotoBase64;
        this.valorFechado = valorFechado;
        this.tipoServico = tipoServico;
        this.servicoContratado = servicoContratado;
    }
    public String getTipoServico() {
        return tipoServico;
    }

    public String getServicoContratado() {
        return servicoContratado;
    }
    public Long getIdCliente() {
        return idCliente;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getFotoBase64() {
        return fotoBase64;
    }

    public Double getValorFechado() {
        return valorFechado;
    }
}
