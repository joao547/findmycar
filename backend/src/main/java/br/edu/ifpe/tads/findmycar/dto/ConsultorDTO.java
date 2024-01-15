package br.edu.ifpe.tads.findmycar.dto;

import java.util.List;
import java.util.Set;

public class ConsultorDTO {
    private final String nome;
    private final List<String> localidades;
    private final Set<String> tipoConsultoria;
    private final Long mediaNota;
    private final String fotoBase64;

    public ConsultorDTO(
        String nome,
        List<String> localidades,
        Set<String> tipoConsultoria,
        Long mediaNota,
        String fotoBase64
    ) {
        this.nome = nome;
        this.localidades = localidades;
        this.tipoConsultoria = tipoConsultoria;
        this.mediaNota = mediaNota;
        this.fotoBase64 = fotoBase64;
    }

    public Long getMediaNota() {
        return mediaNota;
    }

    public String getFotoBase64() {
        return fotoBase64;
    }

    public List<String> getLocalidade() {
        return localidades;
    }

    public String getNome() {
        return nome;
    }

    public Set<String> getTipoConsultoria() {
        return tipoConsultoria;
    }
}
