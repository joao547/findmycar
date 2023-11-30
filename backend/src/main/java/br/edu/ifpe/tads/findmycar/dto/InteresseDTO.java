package br.edu.ifpe.tads.findmycar.dto;

import br.edu.ifpe.tads.findmycar.entity.Interesse;
import br.edu.ifpe.tads.findmycar.entity.Proposta;
import br.edu.ifpe.tads.findmycar.entity.Usuario;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public class InteresseDTO {

    private long id;

    @NotEmpty
    private String marca;

    @NotEmpty
    private String modelo;

    @NotEmpty
    private double faixaDePreco;

    @NotEmpty
    @Size(min = 4, max = 4)
    private String anoMinimo;

    private List<Proposta> propostas;

    public InteresseDTO() {}

    public InteresseDTO(long id, String marca, String modelo, double faixaDePreco, String anoMinimo, List<Proposta> propostas) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.faixaDePreco = faixaDePreco;
        this.anoMinimo = anoMinimo;
        this.propostas = propostas;
    }

    public Interesse buildInteresse(Usuario usuario){
        Interesse interesse = new Interesse();
        interesse.setMarca(this.marca);
        interesse.setModelo(this.modelo);
        interesse.setAnoMinimo(this.anoMinimo);
        interesse.setFaixaDePreco(this.faixaDePreco);
        interesse.setSolicitante(usuario);

        return interesse;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public double getFaixaDePreco() {
        return faixaDePreco;
    }

    public void setFaixaDePreco(double faixaDePreco) {
        this.faixaDePreco = faixaDePreco;
    }

    public String getAnoMinimo() {
        return anoMinimo;
    }

    public void setAnoMinimo(String anoMinimo) {
        this.anoMinimo = anoMinimo;
    }

    public List<Proposta> getPropostas() {
        return propostas;
    }

    public void setPropostas(List<Proposta> propostas) {
        this.propostas = propostas;
    }
}
