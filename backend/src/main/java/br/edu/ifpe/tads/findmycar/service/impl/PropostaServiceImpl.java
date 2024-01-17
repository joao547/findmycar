package br.edu.ifpe.tads.findmycar.service.impl;

import br.edu.ifpe.tads.findmycar.dto.PropostaRetornoDTO;
import br.edu.ifpe.tads.findmycar.entity.Proposta;
import br.edu.ifpe.tads.findmycar.entity.enums.Status;
import br.edu.ifpe.tads.findmycar.infra.security.PropostaDTO;
import br.edu.ifpe.tads.findmycar.repository.ClienteRepository;
import br.edu.ifpe.tads.findmycar.repository.ConsultorRepository;
import br.edu.ifpe.tads.findmycar.repository.PropostaRepository;
import br.edu.ifpe.tads.findmycar.service.PropostaService;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PropostaServiceImpl implements PropostaService {

        private final PropostaRepository propostaRepository;
        private final ClienteRepository clienteRepository;
        private final ConsultorRepository consultorRepository;

        public PropostaServiceImpl(PropostaRepository propostaRepository, ClienteRepository clienteRepository, ConsultorRepository consultorRepository){
            this.propostaRepository = propostaRepository;
            this.clienteRepository = clienteRepository;
            this.consultorRepository = consultorRepository;
        }
        @Override
    public List<PropostaRetornoDTO> getAll() {
            List<Proposta> todasPropostas = propostaRepository.findAll();;
            List<PropostaRetornoDTO> retornoDTOS = new ArrayList<>();
            if(!todasPropostas.isEmpty()){
                for (Proposta proposta : todasPropostas){
                    retornoDTOS.add( gerarRetornoPropostas(proposta));
                }
            }
            return retornoDTOS;
        }
       @Override
       public PropostaRetornoDTO criarProposta(PropostaDTO dto){
            PropostaRetornoDTO retornoDto = gerarRetornoPropostas(this.propostaRepository.save(gerarProposta(dto)));
            //retornoDto.setIdProposta(propostacriada.getId());
            //retornoDto.setDataProposta(propostacriada.getDataDaProposta());
           // retornoDto.setValorAcordado(propostacriada.getValorFechado());
            //retornoDto.setIdCliente(propostacriada.getCliente().getId());
           // retornoDto.setNomeCliente(propostacriada.getCliente().getNome());
           // retornoDto.setIdConsultor(propostacriada.getConsultor().getId());
           // retornoDto.setNomeConsultor(propostacriada.getConsultor().getNome());
           return retornoDto;
        }

        private Proposta gerarProposta(PropostaDTO dto){
            //LocalTime horaAtual = LocalTime.now();
            //Date horaAtualComoDate = java.sql.Timestamp.valueOf(String.valueOf(horaAtual));
            Proposta proposta = new Proposta();
            proposta.setCliente(clienteRepository.getById(dto.getIdCliente()));
            proposta.setConsultor(consultorRepository.getById(dto.getIdConsultor()));
            proposta.setStatus(Status.valueOf("ESPERANDO"));
            proposta.setValorFechado(dto.getValorFechado());
            //proposta.setDataDaProposta(horaAtualComoDate);
            return proposta;
        }

        private PropostaRetornoDTO gerarRetornoPropostas(Proposta propostacriada){
            PropostaRetornoDTO retornoDto = new PropostaRetornoDTO();
            retornoDto.setIdProposta(propostacriada.getId());
            retornoDto.setDataProposta(propostacriada.getDataDaProposta());
            retornoDto.setValorAcordado(propostacriada.getValorFechado());
            retornoDto.setIdCliente(propostacriada.getCliente().getId());
            retornoDto.setNomeCliente(propostacriada.getCliente().getNome());
            retornoDto.setIdConsultor(propostacriada.getConsultor().getId());
            retornoDto.setNomeConsultor(propostacriada.getConsultor().getNome());
            return retornoDto;

        }
}
