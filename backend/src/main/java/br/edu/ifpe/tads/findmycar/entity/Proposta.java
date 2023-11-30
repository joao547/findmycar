package br.edu.ifpe.tads.findmycar.entity;

import br.edu.ifpe.tads.findmycar.entity.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "proposta")
public class Proposta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date dataDaPropostaEnviada;

    @Temporal(TemporalType.DATE)
    private Date dataDaPropostaAceita;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String justificativa;

    @ManyToOne
    @JoinColumn(name = "proponente_id", nullable = false)
    private Usuario proponente;

    @ManyToOne
    @JoinColumn(name = "destinatario_id", nullable = false)
    private Usuario destinatario;

    @OneToOne(mappedBy = "propostaAceita")
    private Servico servicoComAceite;

    @ManyToOne
    @JoinColumn(name = "servico_proposta_id", referencedColumnName = "id")
    private Servico servicoProposta;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public Usuario getProponente() {
        return proponente;
    }

    public void setProponente(Usuario proponente) {
        this.proponente = proponente;
    }

    public Usuario getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Usuario destinatario) {
        this.destinatario = destinatario;
    }

    public Servico getServicoComAceite() {
        return servicoComAceite;
    }

    public void setServicoComAceite(Servico servicoComAceite) {
        this.servicoComAceite = servicoComAceite;
    }

    public Servico getServicoProposta() {
        return servicoProposta;
    }

    public void setServicoProposta(Servico servicoProposta) {
        this.servicoProposta = servicoProposta;
    }

    public Date getDataDaPropostaEnviada() {
        return dataDaPropostaEnviada;
    }

    public void setDataDaPropostaEnviada(Date dataDaPropostaEnviada) {
        this.dataDaPropostaEnviada = dataDaPropostaEnviada;
    }

    public Date getDataDaPropostaAceita() {
        return dataDaPropostaAceita;
    }

    public void setDataDaPropostaAceita(Date dataDaPropostaAceita) {
        this.dataDaPropostaAceita = dataDaPropostaAceita;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
