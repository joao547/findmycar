package br.edu.ifpe.tads.findmycar.service;

import br.edu.ifpe.tads.findmycar.entity.Consultor;

import java.util.List;

public interface ConsultorService {
    List<Consultor> getAll();

    public List<Consultor> getByAreasConsultants(List<String> areas) ;

    List<Consultor> getByAreasSeekers(List<Long> locais, List<String> areaAtuacao);
}
