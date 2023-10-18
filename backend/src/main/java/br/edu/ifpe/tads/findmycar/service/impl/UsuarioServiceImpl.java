package br.edu.ifpe.tads.findmycar.service.impl;

import br.edu.ifpe.tads.findmycar.dto.UsuarioDto;
import br.edu.ifpe.tads.findmycar.entity.Cliente;
import br.edu.ifpe.tads.findmycar.entity.Consultor;
import br.edu.ifpe.tads.findmycar.repository.ClienteRepository;
import br.edu.ifpe.tads.findmycar.repository.ConsultorRepository;
import br.edu.ifpe.tads.findmycar.service.UsuarioService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final ConsultorRepository consultorRepository;
    private ClienteRepository clienteRepository;

    private final PasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(ConsultorRepository consultorRepository, ClienteRepository clienteRepository, PasswordEncoder passwordEncoder) {
        this.consultorRepository = consultorRepository;
        this.clienteRepository = clienteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void criarUsuario(UsuarioDto dto) {
        if (isConsultor(dto)){
            criarConsultor(dto);
        }
        else {
            criarCliente(dto);
        }
    }

    private boolean isConsultor(UsuarioDto dto){
        return dto.getAreaDeAtuacao() != null && dto.getDisponibilidade() != null && dto.getPrecoDoServico() != null;
    }

    private void criarConsultor(UsuarioDto dto){
        Consultor consultor = new Consultor();
        consultor.setEmail(dto.getEmail());
        consultor.setNome(dto.getNome());
        consultor.setSenha(passwordEncoder.encode(dto.getSenha()));
        consultor.setDisponibilidade(dto.getDisponibilidade());
        consultor.setAreaDeAtuacao(dto.getAreaDeAtuacao());
        consultor.setPrecoDoServico(dto.getPrecoDoServico());

        this.consultorRepository.save(consultor);
    }

    private void criarCliente(UsuarioDto dto){
        Cliente cliente = new Cliente();
        cliente.setEmail(dto.getEmail());
        cliente.setNome(dto.getNome());
        cliente.setSenha(passwordEncoder.encode(dto.getSenha()));

        this.clienteRepository.save(cliente);
    }
}
