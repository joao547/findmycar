package br.edu.ifpe.tads.findmycar.service.impl;

import br.edu.ifpe.tads.findmycar.entity.Consultor;
import br.edu.ifpe.tads.findmycar.repository.ConsultorRepository;
import br.edu.ifpe.tads.findmycar.service.ConsultorService;
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
}
