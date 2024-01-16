package br.edu.ifpe.tads.findmycar.controller;

import br.edu.ifpe.tads.findmycar.controller.exceptions.BadRequestException;
import br.edu.ifpe.tads.findmycar.dto.UsuarioDto;
import br.edu.ifpe.tads.findmycar.infra.security.AuthenticationSucessfull;
import br.edu.ifpe.tads.findmycar.infra.security.CredencialsDTO;
import br.edu.ifpe.tads.findmycar.infra.security.JWTUtil;
import br.edu.ifpe.tads.findmycar.service.UsuarioService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin("http://127.0.0.1:5173")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private final AuthenticationManager authenticationManager;
  private final JWTUtil jwtUtil;

  private final UsuarioService usuarioService;

  public AuthController(AuthenticationManager authenticationManager, JWTUtil jwtUtil, UsuarioService usuarioService) {
    this.authenticationManager = authenticationManager;
    this.jwtUtil = jwtUtil;
    this.usuarioService = usuarioService;
  }

  @PostMapping("/login")
  public ResponseEntity<AuthenticationSucessfull> login(@RequestBody CredencialsDTO credencialsDTO) {
    Authentication authenticationRequests = UsernamePasswordAuthenticationToken
        .unauthenticated(credencialsDTO.getEmail(), credencialsDTO.getPassword());
    Authentication authenticationResponse = this.authenticationManager.authenticate(authenticationRequests);
    String tipousuario = this.usuarioService.pegarTipo(credencialsDTO.getEmail());
    User usuario = (User) authenticationResponse.getPrincipal();
    String token = jwtUtil.generateToken(usuario.getUsername(), tipousuario);

    AuthenticationSucessfull authenticationSucessfull = new AuthenticationSucessfull(token);

    return ResponseEntity.ok(authenticationSucessfull);
  }

  @PostMapping("/criar")
  public ResponseEntity<?> registrarUsuario(@Valid @RequestParam("pessoaJson") String pessoaJson,
      @RequestParam("file") MultipartFile file) {
    ObjectMapper mapper = new ObjectMapper();

    try {
      UsuarioDto pessoa = mapper.readValue(pessoaJson, UsuarioDto.class);
      this.usuarioService.criarUsuario(pessoa, file);
    } catch (BadRequestException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    } catch (JsonMappingException e) {
      throw new RuntimeException("Não foi possível ler o json");
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Não foi possível ler o json");
    }

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
