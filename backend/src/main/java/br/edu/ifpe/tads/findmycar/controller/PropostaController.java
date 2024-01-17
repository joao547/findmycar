package br.edu.ifpe.tads.findmycar.controller;

import br.edu.ifpe.tads.findmycar.dto.*;
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

    @GetMapping("/propostasConsultor")
    public ResponseEntity propostasConsultor(   @RequestParam(value = "idDono", required = true) Long idDono,
                                                @RequestParam(value = "statusBuscado", required = true) String statusBuscado) {
        List<PropostaRetornoDTO> propostaRetornoDTOS = propostaService.getPropostasConsultor(idDono, statusBuscado);
        return new ResponseEntity<>(propostaRetornoDTOS, HttpStatus.OK);
    }
    @GetMapping("/propostasCliente")
    public ResponseEntity propostasCliente(   @RequestParam(value = "idDono", required = true) Long idDono,
                                              @RequestParam(value = "statusBuscado", required = true) String statusBuscado)  {
        List<PropostaRetornoDTO> propostaRetornoDTOS = propostaService.getPropostasCliente(idDono, statusBuscado);
        return new ResponseEntity<>(propostaRetornoDTOS, HttpStatus.OK);
    }

}
