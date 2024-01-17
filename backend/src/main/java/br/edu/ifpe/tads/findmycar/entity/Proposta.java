package br.edu.ifpe.tads.findmycar.entity;

import br.edu.ifpe.tads.findmycar.entity.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.util.Date;

@Entity
@Table(name = "proposta")
public class Proposta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date dataDaProposta;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "consultor_id", nullable = false)
    @JsonIgnore
    private Consultor consultor;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    @Cascade(org.hibernate.annotations.CascadeType.REFRESH)
    private Cliente cliente;

    private double valorFechado;

    public Date getDataDaProposta() {
        return dataDaProposta;
    }

    public void setDataDaProposta(Date dataDaProposta) {
        this.dataDaProposta = dataDaProposta;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public double getValorFechado() {
        return valorFechado;
    }

    public void setValorFechado(double valorFechado) {
        this.valorFechado = valorFechado;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Consultor getConsultor() {
        return consultor;
    }

    public void setConsultor(Consultor consultor) {
        this.consultor = consultor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
