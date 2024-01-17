package br.edu.ifpe.tads.findmycar.service;

import br.edu.ifpe.tads.findmycar.dto.AceitarPropostaDTO;
import br.edu.ifpe.tads.findmycar.dto.BuscaPropostaDTO;
import br.edu.ifpe.tads.findmycar.dto.PropostaRetornoDTO;
import br.edu.ifpe.tads.findmycar.dto.PropostaDTO;

import java.util.List;

public interface PropostaService {
    public List<PropostaRetornoDTO> getAll() ;
    public PropostaRetornoDTO criarProposta(PropostaDTO dto);

    void aceitarProposta(AceitarPropostaDTO dto);

    List<PropostaRetornoDTO> getPropostasCliente(BuscaPropostaDTO dto);

    List<PropostaRetornoDTO> getPropostasConsultor(BuscaPropostaDTO dto);
}
