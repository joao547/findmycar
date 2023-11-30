package br.edu.ifpe.tads.findmycar.service.impl;

import br.edu.ifpe.tads.findmycar.entity.Usuario;
import br.edu.ifpe.tads.findmycar.infra.security.JWTUtil;
import br.edu.ifpe.tads.findmycar.repository.UsuarioRepository;
import br.edu.ifpe.tads.findmycar.service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TokenServiceImpl implements TokenService {

    private static final int JWT_SUBSTRING = 7;
    private final UsuarioRepository usuarioRepository;
    private final JWTUtil jwtUtil;

    public TokenServiceImpl(UsuarioRepository usuarioRepository, JWTUtil jwtUtil) {
        this.usuarioRepository = usuarioRepository;
        this.jwtUtil = jwtUtil;
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String jwt = "";
        String header = request.getHeader("Authorization");


        if( header != null && header.startsWith("Bearer ")) {
            jwt = header.substring(JWT_SUBSTRING);
        }

        return jwt;
    }

    public Usuario getUsuarioByRequest(HttpServletRequest request){
        String token = getTokenFromRequest(request);

        String email = this.jwtUtil.getEmailFromToken(token);
        Optional<Usuario> usuario = this.usuarioRepository.findUsuarioByEmail(email);

        if (usuario.isEmpty())
            throw new UsernameNotFoundException("User not found");

        return usuario.get();
    }
}
