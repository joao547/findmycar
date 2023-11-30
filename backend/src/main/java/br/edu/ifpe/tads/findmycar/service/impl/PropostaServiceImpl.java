package br.edu.ifpe.tads.findmycar.service.impl;

import br.edu.ifpe.tads.findmycar.controller.exceptions.NotFoundException;
import br.edu.ifpe.tads.findmycar.dto.PropostaDTO;
import br.edu.ifpe.tads.findmycar.entity.Proposta;
import br.edu.ifpe.tads.findmycar.entity.Servico;
import br.edu.ifpe.tads.findmycar.entity.Usuario;
import br.edu.ifpe.tads.findmycar.entity.enums.Status;
import br.edu.ifpe.tads.findmycar.repository.PropostaRepository;
import br.edu.ifpe.tads.findmycar.repository.ServicoRepository;
import br.edu.ifpe.tads.findmycar.repository.UsuarioRepository;
import br.edu.ifpe.tads.findmycar.service.PropostaService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PropostaServiceImpl implements PropostaService {

    private final PropostaRepository propostaRepository;
    private final ServicoRepository servicoRepository;

    private final UsuarioRepository usuarioRepository;

    public PropostaServiceImpl(PropostaRepository propostaRepository, ServicoRepository servicoRepository, UsuarioRepository usuarioRepository) {
        this.propostaRepository = propostaRepository;
        this.servicoRepository = servicoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void criarProposta(PropostaDTO dto, Usuario proponente) throws NotFoundException {
        Optional<Usuario> destinatario = usuarioRepository.findById(dto.getDestinatarioId());
        if (destinatario.isEmpty()){
            throw new NotFoundException("Usuário destinatário não encontrado");
        }
        Optional<Servico> servico = servicoRepository.findById(dto.getServicoId());
        if (servico.isEmpty()){
            throw new NotFoundException("Serviço não encontrado");
        }
        Proposta proposta = new Proposta();
        proposta.setProponente(proponente);
        proposta.setDestinatario(destinatario.get());
        proposta.setServicoProposta(servico.get());
        proposta.setDataDaPropostaEnviada(new Date());
        proposta.setStatus(Status.ESPERANDO);

        propostaRepository.save(proposta);
    }

    @Override
    public void aceitarProposta(Long idProposta, Usuario usuario) throws NotFoundException {
        Optional<Proposta> optionalProposta = propostaRepository.findByIdAndDestinatario(idProposta, usuario);
        if (optionalProposta.isEmpty()){
            throw new NotFoundException("Proposta não encontra");
        }
        Proposta proposta = optionalProposta.get();
        Servico servico = proposta.getServicoProposta();
        servico.setPropostaAceita(proposta);
        servicoRepository.save(servico);

        proposta.setStatus(Status.ACEITO);
        propostaRepository.save(proposta);
    }

    @Override
    public void rejeitarProposta(Long idProposta, Usuario usuario) throws NotFoundException {
        Optional<Proposta> optionalProposta = propostaRepository.findByIdAndDestinatario(idProposta, usuario);
        if (optionalProposta.isEmpty()){
            throw new NotFoundException("Proposta não encontra");
        }
        Proposta proposta = optionalProposta.get();
        proposta.setStatus(Status.NAO_ACEITO);

        propostaRepository.save(proposta);
    }

    @Override
    public List<Proposta> listPropostaByServico(Long servicoId) throws NotFoundException {
        Optional<Servico> servico = servicoRepository.findById(servicoId);
        if (servico.isEmpty()){
            throw new NotFoundException("Serviço não encontrado");
        }
        return propostaRepository.findAllByServicoPropostaAndStatus(servico.get(), Status.ESPERANDO);
    }
}
