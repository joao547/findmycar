package br.edu.ifpe.tads.findmycar.service.impl;

import br.edu.ifpe.tads.findmycar.dto.AceitarPropostaDTO;
import br.edu.ifpe.tads.findmycar.dto.FinalizarPropostaDTO;
import br.edu.ifpe.tads.findmycar.dto.PropostaRetornoDTO;
import br.edu.ifpe.tads.findmycar.entity.Avaliacao;
import br.edu.ifpe.tads.findmycar.entity.Cliente;
import br.edu.ifpe.tads.findmycar.entity.Consultor;
import br.edu.ifpe.tads.findmycar.entity.Proposta;
import br.edu.ifpe.tads.findmycar.entity.enums.Status;
import br.edu.ifpe.tads.findmycar.dto.PropostaDTO;
import br.edu.ifpe.tads.findmycar.repository.AvaliacaoRepository;
import br.edu.ifpe.tads.findmycar.repository.ClienteRepository;
import br.edu.ifpe.tads.findmycar.repository.ConsultorRepository;
import br.edu.ifpe.tads.findmycar.repository.PropostaRepository;
import br.edu.ifpe.tads.findmycar.service.PropostaService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PropostaServiceImpl implements PropostaService {

        private final PropostaRepository propostaRepository;
        private final ClienteRepository clienteRepository;
        private final ConsultorRepository consultorRepository;
        private final AvaliacaoRepository avaliacaoRepository;

        public PropostaServiceImpl(PropostaRepository propostaRepository, ClienteRepository clienteRepository, ConsultorRepository consultorRepository, AvaliacaoRepository avaliacaoRepository){
            this.propostaRepository = propostaRepository;
            this.clienteRepository = clienteRepository;
            this.consultorRepository = consultorRepository;
            this.avaliacaoRepository = avaliacaoRepository;
        }
        @Override
    public List<PropostaRetornoDTO> getAll() {
            List<Proposta> todasPropostas = propostaRepository.findAll();
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
           return retornoDto;
        }

    @Override
    public void aceitarProposta(AceitarPropostaDTO dto) {
        Optional<Proposta> propostarep = propostaRepository.findById(dto.getIdProposta());
        Proposta proposta = propostarep.get();
        if(dto.isFoiaceita()){

           proposta.setStatus(Status.ACEITO);
        }else{
            proposta.setStatus(Status.NAO_ACEITO);
        }
        this.propostaRepository.save(proposta);
    }

    @Override
    public List<PropostaRetornoDTO> getPropostasCliente(Long idDono, String statusBuscado) {
        List<Proposta> todasPropostas;
        if(Objects.equals(statusBuscado, "ALL")){
            Cliente cliente = clienteRepository.getReferenceById(idDono);
            todasPropostas = propostaRepository.getPropostasClienteAll(cliente.getId());
        }else {
            todasPropostas = this.propostaRepository.getPropostasCliente(idDono, Status.valueOf(statusBuscado));
        }
        List<PropostaRetornoDTO> retornoDTOS = new ArrayList<>();
        if(!todasPropostas.isEmpty()){
            for (Proposta proposta : todasPropostas){
                retornoDTOS.add( gerarRetornoPropostas(proposta));
            }
        }
        return retornoDTOS;
    }

    @Override
    public List<PropostaRetornoDTO> getPropostasConsultor(Long idDono, String statusBuscado) {
        List<Proposta> todasPropostas;
        if(Objects.equals(statusBuscado, "ALL")){
            Consultor consultor = consultorRepository.getReferenceById(idDono);
            todasPropostas = propostaRepository.getPropostasConsultorAll(consultor.getId());
        }else{
        todasPropostas = this.propostaRepository.getPropostasConsultor(idDono, Status.valueOf(statusBuscado));
        }
        List<PropostaRetornoDTO> retornoDTOS = new ArrayList<>();
        if(!todasPropostas.isEmpty()){
            for (Proposta proposta : todasPropostas){
                retornoDTOS.add( gerarRetornoPropostas(proposta));
            }
        }
        return retornoDTOS;
    }

    @Override
    public void finalizarProposta(FinalizarPropostaDTO dto) {
        Proposta proposta = this.propostaRepository.getReferenceById(dto.getIdProposta());
        if(Objects.equals(proposta.getStatus().toString(), "ACEITO")){
        Avaliacao avaliacao = new Avaliacao();
        Consultor consultor = this.consultorRepository.getReferenceById(proposta.getConsultor().getId());
        avaliacao.setAvaliado(proposta.getConsultor());
        avaliacao.setAvaliador(proposta.getCliente());
        avaliacao.setNota(dto.getNota());
        avaliacao.setDescricao(dto.getComentario());
        this.avaliacaoRepository.save(avaliacao);
        proposta.setStatus(Status.FINALIZADO);
        this.propostaRepository.save(proposta);
        List<Avaliacao> todasAvaliacoes = this.avaliacaoRepository.getAvaliacoesConsultorAll(consultor);
        double notafinal=0;
        int quantidade =0;
        for(Avaliacao av: todasAvaliacoes){
            notafinal = av.getNota()+ notafinal;
            quantidade++;
        }
        notafinal = notafinal/quantidade;
        consultor.setNota(notafinal);
        this.consultorRepository.save(consultor);
        }
    }



    private Proposta gerarProposta(PropostaDTO dto){
            //LocalTime horaAtual = LocalTime.now();
            //Date horaAtualComoDate = java.sql.Timestamp.valueOf(String.valueOf(horaAtual));
            Proposta proposta = new Proposta();
            proposta.setCliente(clienteRepository.getById(dto.getIdCliente()));
            proposta.setConsultor(consultorRepository.getById(dto.getIdConsultor()));
            proposta.setStatus(Status.ESPERANDO);
            proposta.setValorFechado(dto.getValorFechado());
            proposta.setLocalServico(dto.getLocalServico());
            proposta.setTipoServico(dto.getTipoServico());
            proposta.setServicoContratado(dto.getServicoContratado());
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
            retornoDto.setLocalServico(propostacriada.getLocalServico());
            retornoDto.setTipoServico(propostacriada.getTipoServico());
            retornoDto.setServicoContratado(propostacriada.getServicoContratado());
            retornoDto.setStatusAtual(String.valueOf(propostacriada.getStatus()));
            return retornoDto;

        }
}
