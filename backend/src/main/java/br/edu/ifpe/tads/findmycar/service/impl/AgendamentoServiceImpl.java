package br.edu.ifpe.tads.findmycar.service.impl;

import br.edu.ifpe.tads.findmycar.dto.AgendamentoDTO;
import br.edu.ifpe.tads.findmycar.entity.Agendamento;
import br.edu.ifpe.tads.findmycar.entity.Usuario;
import br.edu.ifpe.tads.findmycar.repository.AgendamentoRepository;
import br.edu.ifpe.tads.findmycar.service.AgendamentoService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AgendamentoServiceImpl implements AgendamentoService {

    private final AgendamentoRepository repository;

    public AgendamentoServiceImpl(AgendamentoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void criarAgendamento(AgendamentoDTO dto, Usuario usuario) throws UsernameNotFoundException {
        Agendamento agendamento = dto.buildAgendamento(usuario);
        repository.save(agendamento);
    }
}
