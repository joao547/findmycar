package br.edu.ifpe.tads.findmycar.controller;

import br.edu.ifpe.tads.findmycar.dto.InteresseDTO;
import br.edu.ifpe.tads.findmycar.dto.UsuarioDto;
import br.edu.ifpe.tads.findmycar.entity.Interesse;
import br.edu.ifpe.tads.findmycar.entity.Usuario;
import br.edu.ifpe.tads.findmycar.service.InteresseService;
import br.edu.ifpe.tads.findmycar.service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://127.0.0.1:5173")
@RestController
@RequestMapping("/api/interesse")
public class InteresseController {

    private final TokenService tokenService;
    private final InteresseService interesseService;

    public InteresseController(TokenService tokenService, InteresseService interesseService) {
        this.tokenService = tokenService;
        this.interesseService = interesseService;
    }

    @PutMapping
    public ResponseEntity<Void> criarInteresse(
            @Valid @RequestBody InteresseDTO dto,
            HttpServletRequest request
    ) {
        Usuario usuarioLogado = tokenService.getUsuarioByRequest(request);

        interesseService.criarInteresse(dto, usuarioLogado);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
