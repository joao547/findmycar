package br.edu.ifpe.tads.findmycar.controller;

import br.edu.ifpe.tads.findmycar.dto.AceitarPropostaDTO;
import br.edu.ifpe.tads.findmycar.dto.PropostaRetornoDTO;
import br.edu.ifpe.tads.findmycar.dto.UsuarioDto;
import br.edu.ifpe.tads.findmycar.dto.PropostaDTO;
import br.edu.ifpe.tads.findmycar.service.PropostaService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/proposta")
public class PropostaController {

    private final PropostaService propostaService;

    public PropostaController(PropostaService propostaService) { this.propostaService = propostaService;}

    @PostMapping("/criar")
    public ResponseEntity<?> criarProposta(@RequestBody PropostaDTO propostaDTO) {


        PropostaRetornoDTO propostaCriada = this.propostaService.criarProposta(propostaDTO);

        return new ResponseEntity<>(propostaCriada, HttpStatus.CREATED);
        //return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/todos")
    public ResponseEntity getAll(
    ) {
        List<PropostaRetornoDTO> propostaRetornoDTOS = propostaService.getAll();
        return new ResponseEntity<>(propostaRetornoDTOS, HttpStatus.OK);
    }

    @PutMapping("/aceitarNegar")
    public ResponseEntity<Void> atualizarUsuario(
            @RequestBody AceitarPropostaDTO dto) {

        this.propostaService.aceitarProposta(dto);

        return ResponseEntity.noContent().build();
    }

}
