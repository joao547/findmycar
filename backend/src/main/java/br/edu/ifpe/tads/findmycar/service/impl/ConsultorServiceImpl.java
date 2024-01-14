package br.edu.ifpe.tads.findmycar.service.impl;

import br.edu.ifpe.tads.findmycar.entity.Consultor;
import br.edu.ifpe.tads.findmycar.repository.ConsultorRepository;
import br.edu.ifpe.tads.findmycar.service.ConsultorService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultorServiceImpl implements ConsultorService {

    private final ConsultorRepository consultorRepository;

    public ConsultorServiceImpl(ConsultorRepository consultorRepository) {
        this.consultorRepository = consultorRepository;
    }

    @Override
    public List<Consultor> getAll() {
        return consultorRepository.findAll();
    }


    @Override
    public List<Consultor> getByAreasConsultants(List<String> areas) {
        List<Consultor> consultores = consultorRepository.findByAreasConsultants(areas);
       // return this.consultorRepository.findByAreasBuscadorIn(areas);
        if (consultores.isEmpty()) {
            return (List<Consultor>) ResponseEntity.notFound().build();
        }
        return consultores;
    }

    @Override
    public List<Consultor> getByAreasSeekers(List<Long> locais, List<String> areaAtuacao) {
        List<Consultor> consultores = this.consultorRepository.getSeekers(locais, areaAtuacao);
        if (consultores.isEmpty()) {
            return (List<Consultor>) ResponseEntity.notFound().build();
        }
        return consultores;
    }
}
