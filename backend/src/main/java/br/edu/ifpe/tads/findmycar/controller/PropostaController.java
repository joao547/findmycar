package br.edu.ifpe.tads.findmycar.controller;

import br.edu.ifpe.tads.findmycar.controller.exceptions.NotFoundException;
import br.edu.ifpe.tads.findmycar.dto.InteresseDTO;
import br.edu.ifpe.tads.findmycar.dto.PropostaDTO;
import br.edu.ifpe.tads.findmycar.entity.Proposta;
import br.edu.ifpe.tads.findmycar.entity.Usuario;
import br.edu.ifpe.tads.findmycar.service.PropostaService;
import br.edu.ifpe.tads.findmycar.service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://127.0.0.1:5173")
@RestController
@RequestMapping("/api/proposta")
public class PropostaController {

    private final TokenService tokenService;
    private final PropostaService propostaService;


    public PropostaController(TokenService tokenService, PropostaService propostaService) {
        this.tokenService = tokenService;
        this.propostaService = propostaService;
    }

    @PostMapping()
    public ResponseEntity criarProposta(
            HttpServletRequest request,
            @Valid @RequestBody PropostaDTO dto
    ) {
        try {
            Usuario usuarioLogado = tokenService.getUsuarioByRequest(request);
            propostaService.criarProposta(dto, usuarioLogado);

            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{idProposta}/aceitar")
    public ResponseEntity aceitarProposta(
            HttpServletRequest request,
            @PathVariable String idProposta
    ) {
        try {
            Usuario usuarioLogado = tokenService.getUsuarioByRequest(request);
            propostaService.aceitarProposta(Long.parseLong(idProposta), usuarioLogado);

            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch(NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id da proposta está errado");
        }
    }

    @PutMapping("/{idProposta}/rejeitar")
    public ResponseEntity rejeitarProposta(
            HttpServletRequest request,
            @PathVariable String idProposta
    ) {
        try {
            Usuario usuarioLogado = tokenService.getUsuarioByRequest(request);
            propostaService.rejeitarProposta(Long.parseLong(idProposta), usuarioLogado);

            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch(NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id da proposta está errado");
        }
    }

    @GetMapping("/servico/{idServico}")
    public ResponseEntity listPropostaByServico(
            HttpServletRequest request,
            @PathVariable String idServico
    ){
        try {
            Usuario usuarioLogado = tokenService.getUsuarioByRequest(request);
            List<Proposta> propostas = propostaService.listPropostaByServico(Long.parseLong(idServico));
            return ResponseEntity.ok(propostas);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch(NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id do serviço está errado");
        }
    }
}
