package br.edu.ifpe.tads.findmycar.dto;

import br.edu.ifpe.tads.findmycar.entity.Agendamento;
import br.edu.ifpe.tads.findmycar.entity.Proposta;
import br.edu.ifpe.tads.findmycar.entity.Usuario;
import jakarta.validation.constraints.NotEmpty;

import java.util.Date;
import java.util.List;

public class AgendamentoDTO {

    private long id;

    @NotEmpty
    private String marca;

    @NotEmpty
    private String modelo;

    @NotEmpty
    private Date data;

    @NotEmpty
    private String local;

    private List<Proposta> propostas;

    public AgendamentoDTO(){}

    public AgendamentoDTO(long id, String marca, String modelo, Date data, String local, List<Proposta> propostas) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.data = data;
        this.local = local;
        this.propostas = propostas;
    }

    public Agendamento buildAgendamento(Usuario usuario){
        Agendamento agendamento = new Agendamento();
        agendamento.setMarca(this.marca);
        agendamento.setModelo(this.modelo);
        agendamento.setData(this.data);
        agendamento.setLocal(this.local);
        agendamento.setSolicitante(usuario);

        return agendamento;
    }
}
