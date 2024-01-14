package br.edu.ifpe.tads.findmycar.service;

import br.edu.ifpe.tads.findmycar.controller.exceptions.BadRequestException;
import br.edu.ifpe.tads.findmycar.dto.UsuarioDTOInfo;
import br.edu.ifpe.tads.findmycar.dto.UsuarioDto;
import br.edu.ifpe.tads.findmycar.entity.Usuario;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface UsuarioService {

  void criarUsuario(UsuarioDto dto, MultipartFile file) throws BadRequestException;

  Optional<Usuario> findUsuarioByEmail(String email);

  Optional<UsuarioDTOInfo> findUsuarioByJWT(String jwt);

  void atualizarUsuario(UsuarioDto dto, String tokenJWT);
}
