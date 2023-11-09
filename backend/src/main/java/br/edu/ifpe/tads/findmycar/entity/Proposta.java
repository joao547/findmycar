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
    private Date dataDaProposta;

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
    private Servico servico;

    @ManyToOne
    @JoinColumn(name = "servico_proposta_id")
    private Interesse servicoProposta;

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
}
