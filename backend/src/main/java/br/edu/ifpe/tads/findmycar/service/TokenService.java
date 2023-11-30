package br.edu.ifpe.tads.findmycar.service;

import br.edu.ifpe.tads.findmycar.entity.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface TokenService {

    Usuario getUsuarioByRequest(HttpServletRequest request) throws UsernameNotFoundException;
}
