package br.edu.ifpe.tads.findmycar.controller;

import br.edu.ifpe.tads.findmycar.entity.CarroMarcas;
import br.edu.ifpe.tads.findmycar.entity.ServicosBuscador;
import br.edu.ifpe.tads.findmycar.repository.CarroMarcasRepository;
import br.edu.ifpe.tads.findmycar.repository.ServicosBuscadorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/consultas")
public class CadastroController {

    private final CarroMarcasRepository carroMarcasRepository;
    private final ServicosBuscadorRepository servicosBuscadorRepository;


    public CadastroController(CarroMarcasRepository carroMarcasRepository, ServicosBuscadorRepository servicosBuscadorRepository) {
        this.carroMarcasRepository = carroMarcasRepository;
        this.servicosBuscadorRepository = servicosBuscadorRepository;
    }

    @GetMapping("/carro_marcas")
    public ResponseEntity<List<CarroMarcas>> recuperarMarcasDeCarros() {
        List<CarroMarcas> carroMarcasList = this.carroMarcasRepository.findAll();

        return ResponseEntity.ok(carroMarcasList);
    }

    @GetMapping("/servicos_buscador")
    public ResponseEntity<List<ServicosBuscador>> recuperarServicos() {
        List<ServicosBuscador> servicoBuscadors = this.servicosBuscadorRepository.findAll();

        return ResponseEntity.ok(servicoBuscadors);
    }
}

