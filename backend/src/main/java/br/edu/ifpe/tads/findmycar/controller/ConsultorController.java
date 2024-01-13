package br.edu.ifpe.tads.findmycar.controller;

import br.edu.ifpe.tads.findmycar.dto.UsuarioDTOInfo;
import br.edu.ifpe.tads.findmycar.entity.Consultor;
import br.edu.ifpe.tads.findmycar.service.ConsultorService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

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

    /*@GetMapping("/buscarBuscadores")
    public ResponseEntity getByAreasConsultants(@RequestParam("areas") List<String> areas) {
        List<Consultor> consultoresPorAreas = consultorService.getByAreasConsultants(areas);
        if (consultoresPorAreas.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(consultoresPorAreas);
    }*/
    @GetMapping("/buscarBuscadores")
    public ResponseEntity getByAreasConsultants(@RequestBody Map<String, List<String>> requestBody) {
        List<String> areas = requestBody.get("areas");
        List<Consultor> consultoresPorAreas = consultorService.getByAreasConsultants(areas);
        if (consultoresPorAreas.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(consultoresPorAreas);
    }
    @GetMapping("/buscarConsultores")
    public List<Consultor> getByAreasSeekers(
            @RequestParam(value = "locais", required = false) List<Long> locais,
            @RequestParam(value = "areaAtuacao", required = false) List<String> areaAtuacao) {
        List<Consultor> consultoresPorAreas = consultorService.getByAreasSeekers(locais, areaAtuacao);
        if (consultoresPorAreas.isEmpty()) {
            return (List<Consultor>) ResponseEntity.notFound().build();
        }
        return (List<Consultor>) ResponseEntity.ok(consultoresPorAreas);
    }
}
