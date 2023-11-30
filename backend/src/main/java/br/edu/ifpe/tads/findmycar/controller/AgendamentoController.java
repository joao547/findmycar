package br.edu.ifpe.tads.findmycar.controller;

import br.edu.ifpe.tads.findmycar.dto.AgendamentoDTO;
import br.edu.ifpe.tads.findmycar.entity.Usuario;
import br.edu.ifpe.tads.findmycar.service.AgendamentoService;
import br.edu.ifpe.tads.findmycar.service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://127.0.0.1:5173")
@RestController
@RequestMapping("/api/agendamento")
public class AgendamentoController {

    private final TokenService tokenService;

    private final AgendamentoService agendamentoService;

    public AgendamentoController(TokenService tokenService, AgendamentoService agendamentoService) {
        this.tokenService = tokenService;
        this.agendamentoService = agendamentoService;
    }

    @PutMapping
    public ResponseEntity<Void> criarAgendamento(
            @Valid @RequestBody AgendamentoDTO dto,
            HttpServletRequest request
    ) {
        Usuario usuarioLogado = tokenService.getUsuarioByRequest(request);

        agendamentoService.criarAgendamento(dto, usuarioLogado);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
