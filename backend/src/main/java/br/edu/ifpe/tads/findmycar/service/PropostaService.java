package br.edu.ifpe.tads.findmycar.service;

import br.edu.ifpe.tads.findmycar.dto.PropostaRetornoDTO;
import br.edu.ifpe.tads.findmycar.entity.Proposta;
import br.edu.ifpe.tads.findmycar.infra.security.PropostaDTO;

import java.util.List;

public interface PropostaService {
    public List<PropostaRetornoDTO> getAll() ;
    public PropostaRetornoDTO criarProposta(PropostaDTO dto);

}
