package br.edu.ifpe.tads.findmycar.dto;

public class AceitarPropostaDTO {
    private Long idProposta;
    private boolean foiaceita;

    public AceitarPropostaDTO() {
    }

    public Long getIdProposta() {
        return idProposta;
    }

    public void setIdProposta(Long idProposta) {
        this.idProposta = idProposta;
    }

    public boolean isFoiaceita() {
        return foiaceita;
    }

    public void setFoiaceita(boolean foiaceita) {
        this.foiaceita = foiaceita;
    }
}
