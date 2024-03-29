package br.edu.ifpe.tads.findmycar.controller;

import br.edu.ifpe.tads.findmycar.dto.ClienteDTO;
import br.edu.ifpe.tads.findmycar.dto.ConsultorDTO;
import br.edu.ifpe.tads.findmycar.dto.UsuarioDTOInfo;
import br.edu.ifpe.tads.findmycar.entity.Consultor;
import br.edu.ifpe.tads.findmycar.service.ConsultorService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/consultor")
public class ConsultorController {

    private final ConsultorService consultorService;

    public ConsultorController(ConsultorService consultorService) {
        this.consultorService = consultorService;
    }

    @GetMapping("/todos")
    public ResponseEntity getAll(
    ) {
        return ResponseEntity.ok(consultorService.getAll());
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ConsultorDTO>> buscarConsultor(
        @RequestParam(value = "tipoConsultor", defaultValue = "buscador") String tipoConsultor,
        @RequestParam(value = "servicoBuscador", required = false) String[] servicoBuscador,
        @RequestParam(value = "locaisAtuacao", required = false) String locaisAtuacao,
        @RequestParam(value = "carroMarcas", required = false) String[] carroMarcas

    ) {List<ConsultorDTO> consultores = consultorService.getConsultores(
            tipoConsultor,
            convertArrayToList(servicoBuscador),
            locaisAtuacao,
            convertArrayToList(carroMarcas)
        );

            return ResponseEntity.ok(consultores);
    }

    private List<String> convertArrayToList(String[] array) {
        if (array != null) {
            return List.of(array);
        } else {
            return null;
        }
    }

    @GetMapping("/buscarclientes")
    public ResponseEntity<List<ClienteDTO>> buscarMeusClientes(
            @RequestParam(value = "idConsultor") Long idConsultor

    ) {List<ClienteDTO> clientes = consultorService.getMeusClientes(idConsultor);

        return ResponseEntity.ok(clientes);
    }


}
