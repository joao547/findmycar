package br.edu.ifpe.tads.findmycar.dto;

import br.edu.ifpe.tads.findmycar.entity.CarroMarcas;
import br.edu.ifpe.tads.findmycar.entity.Local;
import br.edu.ifpe.tads.findmycar.entity.ServicosBuscador;

import java.util.List;
import java.util.Set;

public class ConsultorDTO {
    private final Long idConsultor;
    private final String nome;
    private final Set<Local> localidades;
    private final Set<ServicosBuscador> servicosBuscador;
    private final Set<CarroMarcas> carroMarcas;
    private final Long mediaNota;
    private final double precoServico;
    private final String fotoBase64;


    public ConsultorDTO(
            Long idConsultor,
            String nome,
            Set<Local> localidades,
            Set<ServicosBuscador> servicosBuscador,
            Set<CarroMarcas> carroMarcas,
            Long mediaNota, double precoServico,
            String fotoBase64
    ) {
        this.idConsultor = idConsultor;
        this.nome = nome;
        this.localidades = localidades;
        this.servicosBuscador = servicosBuscador;
        this.carroMarcas = carroMarcas;
        this.mediaNota = mediaNota;
        this.precoServico = precoServico;
        this.fotoBase64 = fotoBase64;
    }


    public double getPrecoServico() {
        return precoServico;
    }

    public Long getIdConsultor() {
        return idConsultor;
    }

    public Long getMediaNota() {
        return mediaNota;
    }

    public String getFotoBase64() {
        return fotoBase64;
    }

    public Set<Local> getLocalidade() {
        return localidades;
    }

    public String getNome() {
        return nome;
    }

    public Set<Local> getLocalidades() {
        return localidades;
    }

    public Set<ServicosBuscador> getServicosBuscador() {
        return servicosBuscador;
    }

    public Set<CarroMarcas> getCarroMarcas() {
        return carroMarcas;
    }
}
