package br.edu.ifpe.tads.findmycar.dto;

import br.edu.ifpe.tads.findmycar.entity.Proposta;
import br.edu.ifpe.tads.findmycar.entity.Usuario;
import jakarta.validation.constraints.NotEmpty;

public class PropostaDTO {
    private Long id;

    private Long servicoId;

    @NotEmpty
    private Long destinatarioId;

    public PropostaDTO(){}

    public PropostaDTO(Long id, Long servicoId, Long destinatarioId){
        this.id = id;
        this.servicoId = servicoId;
        this.destinatarioId = destinatarioId;
    }

    public Long getServicoId() {
        return servicoId;
    }

    public void setServicoId(Long servicoId) {
        this.servicoId = servicoId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDestinatarioId() {
        return destinatarioId;
    }

    public void setDestinatarioId(Long destinatarioId) {
        this.destinatarioId = destinatarioId;
    }
}
