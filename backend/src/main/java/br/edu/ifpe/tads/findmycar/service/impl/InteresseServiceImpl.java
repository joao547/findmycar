package br.edu.ifpe.tads.findmycar.service.impl;

import br.edu.ifpe.tads.findmycar.dto.InteresseDTO;
import br.edu.ifpe.tads.findmycar.entity.Interesse;
import br.edu.ifpe.tads.findmycar.entity.Usuario;
import br.edu.ifpe.tads.findmycar.repository.InteresseRepository;
import br.edu.ifpe.tads.findmycar.service.InteresseService;
import org.springframework.stereotype.Service;

@Service
public class InteresseServiceImpl implements InteresseService {

    private final InteresseRepository interesseRepository;

    public InteresseServiceImpl(InteresseRepository interesseRepository) {
        this.interesseRepository = interesseRepository;
    }

    @Override
    public void criarInteresse(InteresseDTO dto, Usuario usuario){
        Interesse interesse = dto.buildInteresse(usuario);
        interesseRepository.save(interesse);
    }
}
