package br.edu.ifpe.tads.findmycar.controller;


import br.edu.ifpe.tads.findmycar.dto.UsuarioDto;
import br.edu.ifpe.tads.findmycar.infra.security.AuthenticationSucessfull;
import br.edu.ifpe.tads.findmycar.infra.security.CredencialsDTO;
import br.edu.ifpe.tads.findmycar.infra.security.JWTUtil;
import br.edu.ifpe.tads.findmycar.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    private final UsuarioService usuarioService;

    public LoginController(AuthenticationManager authenticationManager, JWTUtil jwtUtil, UsuarioService usuarioService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationSucessfull> login(@RequestBody CredencialsDTO credencialsDTO) {
        Authentication authenticationRequests = UsernamePasswordAuthenticationToken
            .unauthenticated(credencialsDTO.getEmail(), credencialsDTO.getPassword());
        Authentication authenticationResponse = this.authenticationManager.authenticate(authenticationRequests);

        User usuario = (User) authenticationResponse.getPrincipal();
        String token = jwtUtil.generateToken(usuario.getUsername());

        return ResponseEntity.ok(new AuthenticationSucessfull(token));
    }

    @PostMapping("/criar")
    public ResponseEntity registrarUsuario(@Valid @RequestBody UsuarioDto dto){
        this.usuarioService.criarUsuario(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
