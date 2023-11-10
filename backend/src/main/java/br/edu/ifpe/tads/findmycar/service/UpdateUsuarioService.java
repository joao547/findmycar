package br.edu.ifpe.tads.findmycar.service;

import br.edu.ifpe.tads.findmycar.dto.UsuarioDto;

public interface UpdateUsuarioService {
    void updateUsuarioService(UsuarioDto usuarioDto, String tokenJWT);
}
