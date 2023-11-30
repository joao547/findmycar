package br.edu.ifpe.tads.findmycar.service;

import br.edu.ifpe.tads.findmycar.dto.AgendamentoDTO;
import br.edu.ifpe.tads.findmycar.entity.Usuario;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface AgendamentoService {

    void criarAgendamento(AgendamentoDTO dto, Usuario usuario)  throws UsernameNotFoundException;
}
