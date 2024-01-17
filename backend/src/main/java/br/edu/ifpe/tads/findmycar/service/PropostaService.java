package br.edu.ifpe.tads.findmycar.service;

import br.edu.ifpe.tads.findmycar.dto.PropostaRetornoDTO;
import br.edu.ifpe.tads.findmycar.dto.PropostaDTO;

import java.util.List;

public interface PropostaService {
    public List<PropostaRetornoDTO> getAll() ;
    public PropostaRetornoDTO criarProposta(PropostaDTO dto);

}
