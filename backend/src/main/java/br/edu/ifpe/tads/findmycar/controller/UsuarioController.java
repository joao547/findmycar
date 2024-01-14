package br.edu.ifpe.tads.findmycar.controller;

import br.edu.ifpe.tads.findmycar.dto.UsuarioDTOInfo;
import br.edu.ifpe.tads.findmycar.dto.UsuarioDto;
import br.edu.ifpe.tads.findmycar.service.UpdateUsuarioService;
import br.edu.ifpe.tads.findmycar.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin("http://127.0.0.1:5173")
@RestController
@RequestMapping("/api/user")
public class UsuarioController {
    private static final int JWT_SUBSTRING = 7;
    private final UsuarioService usuarioService;
    private final UpdateUsuarioService updateUsuarioService;

    public UsuarioController(UsuarioService usuarioService, UpdateUsuarioService updateUsuarioService) {
        this.usuarioService = usuarioService;
        this.updateUsuarioService = updateUsuarioService;
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
        @RequestBody UsuarioDto dto,
        HttpServletRequest request
    ) {
        String tokenJWT = getTokenFromRequest(request);

        updateUsuarioService.updateUsuarioService(dto, tokenJWT);

        return ResponseEntity.noContent().build();
    }
}
