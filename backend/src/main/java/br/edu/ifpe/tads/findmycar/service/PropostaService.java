package br.edu.ifpe.tads.findmycar.service;

import br.edu.ifpe.tads.findmycar.infra.security.PropostaDTO;
import br.edu.ifpe.tads.findmycar.repository.PropostaRepository;

public class PropostaService {

    private final PropostaRepository propostaRepository;

    public PropostaService(PropostaRepository propostaRepository){
        this.propostaRepository = propostaRepository;
    }

    public void criarProposta(PropostaDTO dto){
        //this.propostaRepository.save()
    }

}
