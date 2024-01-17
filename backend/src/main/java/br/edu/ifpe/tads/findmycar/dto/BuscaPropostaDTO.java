package br.edu.ifpe.tads.findmycar.dto;

public class BuscaPropostaDTO {

    private Long idDono;
    private String statusBuscado;

    public BuscaPropostaDTO() {
    }

    public Long getIdDono() {
        return idDono;
    }

    public void setIdDono(Long idDono) {
        this.idDono = idDono;
    }

    public String getStatusBuscado() {
        return statusBuscado;
    }

    public void setStatusBuscado(String statusBuscado) {
        this.statusBuscado = statusBuscado;
    }
}
