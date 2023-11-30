package br.edu.ifpe.tads.findmycar.service;

import br.edu.ifpe.tads.findmycar.controller.exceptions.BadRequestException;
import br.edu.ifpe.tads.findmycar.dto.InteresseDTO;
import br.edu.ifpe.tads.findmycar.entity.Interesse;
import br.edu.ifpe.tads.findmycar.entity.Usuario;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface InteresseService {

    void criarInteresse(InteresseDTO dto, Usuario usuario)  throws UsernameNotFoundException;
}
