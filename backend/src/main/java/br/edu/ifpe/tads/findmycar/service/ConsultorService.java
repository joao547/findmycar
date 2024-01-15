package br.edu.ifpe.tads.findmycar.service;

import br.edu.ifpe.tads.findmycar.dto.ConsultorDTO;
import br.edu.ifpe.tads.findmycar.entity.Consultor;

import java.util.List;

public interface ConsultorService {
    List<Consultor> getAll();

    public List<Consultor> getBuscadores(List<String> areas) ;

    List<Consultor> getMecanicos(List<Long> locais, List<String> areaAtuacao);

    List<ConsultorDTO> getConsultores(String tipoConsultor, List<String> areasBuscador, List<Long> locaisAtuacao, List<String> areasConsultor);
}
