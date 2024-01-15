package br.edu.ifpe.tads.findmycar.service.impl;

import br.edu.ifpe.tads.findmycar.dto.ConsultorDTO;
import br.edu.ifpe.tads.findmycar.entity.Consultor;
import br.edu.ifpe.tads.findmycar.repository.ConsultorRepository;
import br.edu.ifpe.tads.findmycar.service.ConsultorService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConsultorServiceImpl implements ConsultorService {

    private final ConsultorRepository consultorRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public ConsultorServiceImpl(ConsultorRepository consultorRepository) {
        this.consultorRepository = consultorRepository;
    }

    @Override
    public List<Consultor> getAll() {
        return consultorRepository.findAll();
    }


    public List<Consultor> getBuscadores(List<String> areas) {
        return consultorRepository.getBuscadores(areas);
    }

    public List<Consultor> getMecanicos(List<Long> locais, List<String> areaAtuacao) {
        List<Consultor> consultores = this.consultorRepository.getMecanicos(locais);
        if (consultores.isEmpty()) {
            return (List<Consultor>) ResponseEntity.notFound().build();
        }
        return consultores;
    }
    @Override
    public List<ConsultorDTO> getConsultores(String tipoConsultor, List<String> areasBuscador, List<Long> locaisAtuacao, List<String> areasConsultor){
        List<Consultor> consultores = new ArrayList<>();

        if (tipoConsultor.equals("buscador")) {
            consultores = this.getBuscadores(areasBuscador);
        } else {
            consultores = this.getMecanicos(locaisAtuacao, areasConsultor);
        }

        return consultores.stream().map(consultor -> new ConsultorDTO(
            consultor.getNome(),
            null,
            null,
            5L,
            recuperarArquivo(consultor.getFotoPerfil())
        )).toList();
    }

    private String recuperarArquivo(String fileName) {
        return fileName;
//        try {
//            Path filePath = Path.of(uploadDir, fileName);
//            byte[] fileContent = Files.readAllBytes(filePath);
//            return java.util.Base64.getEncoder().encodeToString(fileContent);
//        } catch (Exception e) {
//            System.out.printf("Erro ao buscar o arquivo de nome [%s]", fileName);
//            return "";
//        }
    }
}
