package br.edu.ifpe.tads.findmycar.controller;

import br.edu.ifpe.tads.findmycar.controller.exceptions.BadRequestException;
import br.edu.ifpe.tads.findmycar.dto.UsuarioDto;
import br.edu.ifpe.tads.findmycar.infra.security.CredencialsDTO;
import br.edu.ifpe.tads.findmycar.infra.security.PropostaDTO;
import br.edu.ifpe.tads.findmycar.service.PropostaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/proposta")
public class PropostaController {

    private final PropostaService propostaService;

    public PropostaController(PropostaService propostaService) {
        this.propostaService = propostaService;
    }

    @PostMapping("/criar")
    public ResponseEntity<?> criarProposta(@RequestBody PropostaDTO propostaDTO) {


        this.propostaService.criarProposta(propostaDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
