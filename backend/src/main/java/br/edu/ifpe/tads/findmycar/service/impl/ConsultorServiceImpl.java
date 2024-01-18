package br.edu.ifpe.tads.findmycar.service.impl;

import br.edu.ifpe.tads.findmycar.dto.ClienteDTO;
import br.edu.ifpe.tads.findmycar.dto.ConsultorDTO;
import br.edu.ifpe.tads.findmycar.entity.Cliente;
import br.edu.ifpe.tads.findmycar.entity.Consultor;
import br.edu.ifpe.tads.findmycar.entity.Proposta;
import br.edu.ifpe.tads.findmycar.entity.enums.Status;
import br.edu.ifpe.tads.findmycar.repository.ClienteRepository;
import br.edu.ifpe.tads.findmycar.repository.ConsultorRepository;
import br.edu.ifpe.tads.findmycar.repository.PropostaRepository;
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

    private final PropostaRepository propostaRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public ConsultorServiceImpl(ConsultorRepository consultorRepository,  PropostaRepository propostaRepository) {
        this.consultorRepository = consultorRepository;
        this.propostaRepository = propostaRepository;
    }

    @Override
    public List<Consultor> getAll() {
        return consultorRepository.findAll();
    }


    public List<Consultor> getBuscadores(List<String> areas) {
        List<Long> idsArea = new ArrayList<>();
        for(String area: areas){
            idsArea.add(Long.parseLong(area));
        }
        return consultorRepository.getBuscadores(idsArea);
    }

    public List<Consultor> getMecanicos(String locais, List<String> carroMarcas) {
        List<Long> idsMarca = new ArrayList<>();
        for(String marca: carroMarcas){
            idsMarca.add(Long.parseLong(marca));
        }

        List<Consultor> consultores = this.consultorRepository.getMecanicos(locais, idsMarca);
        if (consultores.isEmpty()) {
            return (List<Consultor>) ResponseEntity.notFound().build();
        }
        return consultores;
    }
    @Override
    public List<ConsultorDTO> getConsultores(String tipoConsultor, List<String> areasBuscador, String locaisAtuacao, List<String> areasConsultor){
        List<Consultor> consultores = new ArrayList<>();

        if (tipoConsultor.equals("buscador")) {
            consultores = this.getBuscadores(areasBuscador);


            return consultores.stream().map(consultor -> new ConsultorDTO(
                    consultor.getId(),
                    consultor.getNome(),
                    null,
                    consultor.getServicosBuscador(),
                    null,
                    5L,
                    consultor.getPrecoServicoBuscador(),
                    recuperarArquivo(consultor.getFotoPerfil())
            )).toList();
        } else {
            consultores = this.getMecanicos(locaisAtuacao, areasConsultor);
            return consultores.stream().map(consultor -> new ConsultorDTO(
                    consultor.getId(),
                    consultor.getNome(),
                    consultor.getLocais(),
                    null,
                    consultor.getCarroMarcas(),
                    5L,
                    consultor.getPrecoServicoMecanico(),
                    recuperarArquivo(consultor.getFotoPerfil())
            )).toList();
        }


    }

    @Override
    public List<ClienteDTO> getMeusClientes(Long idConsultor) {
        List<ClienteDTO> listRetorn = new ArrayList<>();
            List<Proposta> propostas = this.propostaRepository.getPropostasConsultor(idConsultor, Status.ACEITO);
        if(!propostas.isEmpty()){
            for (Proposta proposta: propostas){
                ClienteDTO clienteDTO = new ClienteDTO( proposta.getCliente().getId(), proposta.getCliente().getNome(), proposta.getCliente().getEmail(),proposta.getCliente().getFotoPerfil(),proposta.getValorFechado(),proposta.getTipoServico(),proposta.getServicoContratado());
                listRetorn.add(clienteDTO);
            }
            return listRetorn;

        }
         return null;
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
