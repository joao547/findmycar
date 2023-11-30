package br.edu.ifpe.tads.findmycar.service;

import br.edu.ifpe.tads.findmycar.controller.exceptions.NotFoundException;
import br.edu.ifpe.tads.findmycar.dto.PropostaDTO;
import br.edu.ifpe.tads.findmycar.entity.Proposta;
import br.edu.ifpe.tads.findmycar.entity.Usuario;

import java.util.List;

public interface PropostaService {

    void criarProposta(PropostaDTO dto, Usuario proponente) throws NotFoundException;

    void aceitarProposta(Long idProposta, Usuario usuario) throws NotFoundException;

    void rejeitarProposta(Long idProposta, Usuario usuario) throws NotFoundException;

    List<Proposta> listPropostaByServico(Long servicoId) throws NotFoundException;
}
