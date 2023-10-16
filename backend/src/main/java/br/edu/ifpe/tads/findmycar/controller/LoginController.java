package br.edu.ifpe.tads.findmycar.controller;


import br.edu.ifpe.tads.findmycar.entity.Usuario;
import br.edu.ifpe.tads.findmycar.infra.security.AuthenticationSucessfull;
import br.edu.ifpe.tads.findmycar.infra.security.CredencialsDTO;
import br.edu.ifpe.tads.findmycar.infra.security.JWTUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    public LoginController(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationSucessfull> login(@RequestBody CredencialsDTO credencialsDTO) {
        Authentication authenticationRequests = UsernamePasswordAuthenticationToken
            .unauthenticated(credencialsDTO.getEmail(), credencialsDTO.getPassword());
        Authentication authenticationResponse = this.authenticationManager.authenticate(authenticationRequests);

        System.out.println("Oi");
        Usuario usuario = (Usuario ) authenticationResponse.getPrincipal();
        String token = jwtUtil.generateToken(usuario.getEmail());

        return ResponseEntity.ok(new AuthenticationSucessfull(token));
    }
}
