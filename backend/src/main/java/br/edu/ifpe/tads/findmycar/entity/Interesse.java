package br.edu.ifpe.tads.findmycar.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "interesse")
public class Interesse extends Servico{

    private String anoMinimo;

    private double faixaDePreco;

    public String getAnoMinimo() {
        return anoMinimo;
    }

    public void setAnoMinimo(String anoMinimo) {
        this.anoMinimo = anoMinimo;
    }

    public double getFaixaDePreco() {
        return faixaDePreco;
    }

    public void setFaixaDePreco(double faixaDePreco) {
        this.faixaDePreco = faixaDePreco;
    }
}
