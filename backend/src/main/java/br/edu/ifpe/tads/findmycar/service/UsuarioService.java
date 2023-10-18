package br.edu.ifpe.tads.findmycar.service;

import br.edu.ifpe.tads.findmycar.controller.exceptions.BadRequestException;
import br.edu.ifpe.tads.findmycar.dto.UsuarioDto;

public interface UsuarioService {

    void criarUsuario(UsuarioDto dto) throws BadRequestException;
}
