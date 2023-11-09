package br.edu.ifpe.tads.findmycar.controller;

import br.edu.ifpe.tads.findmycar.dto.UsuarioDTOInfo;
import br.edu.ifpe.tads.findmycar.dto.UsuarioDto;
import br.edu.ifpe.tads.findmycar.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://127.0.0.1:5173")
@RestController
@RequestMapping("/api/user")
public class UsuarioController {
    private static final int JWT_SUBSTRING = 7;
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String jwt = "";
        String header = request.getHeader("Authorization");


        if( header != null && header.startsWith("Bearer ")) {
            jwt = header.substring(JWT_SUBSTRING);
        }

        return jwt;
    }

    @GetMapping("/me")
    public ResponseEntity<UsuarioDTOInfo> recuperarUsuario(
        HttpServletRequest request
    ) {
        String tokenJWT = getTokenFromRequest(request);

        UsuarioDTOInfo usuarioDTOInfo = usuarioService.findUsuarioByJWT(tokenJWT)
            .orElseThrow(() -> new UsernameNotFoundException("user not found"));

        return ResponseEntity.ok(usuarioDTOInfo);
    }

    @PutMapping
    public ResponseEntity<Void> atualizarUsuario(
        @Valid @RequestBody UsuarioDto dto,
        HttpServletRequest request
    ) {
        String tokenJWT = getTokenFromRequest(request);

        usuarioService.atualizarUsuario(dto, tokenJWT);

        return ResponseEntity.noContent().build();
    }
}
