package br.edu.ifpe.tads.findmycar.dto;

public class FinalizarPropostaDTO {
    private Long idProposta;
    private int nota;
    private String comentario;

    public FinalizarPropostaDTO() {
    }

    public Long getIdProposta() {
        return idProposta;
    }

    public void setIdProposta(Long idProposta) {
        this.idProposta = idProposta;
    }


    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
