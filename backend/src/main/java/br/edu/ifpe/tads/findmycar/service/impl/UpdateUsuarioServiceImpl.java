package br.edu.ifpe.tads.findmycar.service.impl;

import br.edu.ifpe.tads.findmycar.dto.UsuarioDto;
import br.edu.ifpe.tads.findmycar.entity.Cliente;
import br.edu.ifpe.tads.findmycar.entity.Consultor;
import br.edu.ifpe.tads.findmycar.entity.Usuario;
import br.edu.ifpe.tads.findmycar.exceptions.InvalidUserType;
import br.edu.ifpe.tads.findmycar.infra.security.JWTUtil;
import br.edu.ifpe.tads.findmycar.repository.ClienteRepository;
import br.edu.ifpe.tads.findmycar.repository.ConsultorRepository;
import br.edu.ifpe.tads.findmycar.repository.UsuarioRepository;
import br.edu.ifpe.tads.findmycar.service.UpdateUsuarioService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UpdateUsuarioServiceImpl implements UpdateUsuarioService {
    private final ConsultorRepository consultorRepository;
    private final ClienteRepository clienteRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;
    private final JWTUtil jwtUtil;

    public UpdateUsuarioServiceImpl(ConsultorRepository consultorRepository, ClienteRepository clienteRepository, PasswordEncoder passwordEncoder, UsuarioRepository usuarioRepository, JWTUtil jwtUtil) {
        this.consultorRepository = consultorRepository;
        this.clienteRepository = clienteRepository;
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void updateUsuarioService(UsuarioDto usuarioDto, String tokenJWT) {
        String email = jwtUtil.getEmailFromToken(tokenJWT);
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario nao encontrado"));

        if (usuarioDto.getTipo().getName().equalsIgnoreCase("consultor")) {
            Consultor consultor = this.consultorRepository.findById(usuario.getId())
                    .orElseThrow(() -> new InvalidUserType(String.format("O usuario de email [%s] nao eh do tipo [%s]", email, usuarioDto.getTipo().getName())));
           updateConsultor(usuarioDto, consultor);
        }

        if (usuarioDto.getTipo().getName().equalsIgnoreCase("cliente")) {
            Cliente cliente = this.clienteRepository.findById(usuario.getId())
                    .orElseThrow(() -> new InvalidUserType(String.format("O usuario de email [%s] nao eh do tipo [%s]", email, usuarioDto.getTipo().getName())));
            updateCliente(usuarioDto, cliente);
        }

    }

    private void updateConsultor(UsuarioDto dto, Consultor consultor){
       if (dto.getNome() != null && !dto.getNome().isEmpty()) {
           consultor.setNome(dto.getNome());
       }

       if (dto.getSenha() != null && !dto.getSenha().isEmpty()) {
           consultor.setSenha(passwordEncoder.encode(dto.getSenha()));
       }

      /* if (dto.getDisponibilidade() != null && !dto.getDisponibilidade().isEmpty()) {
           consultor.setDisponibilidade(dto.getDisponibilidade());
       }

       if (dto.getAreaDeAtuacao() != null && !dto.getAreaDeAtuacao().isEmpty()) {
           consultor.setAreaDeAtuacao(dto.getAreaDeAtuacao());
       }

       if (dto.getPrecoDoServico() != null && dto.getPrecoDoServico() != 0.0) {
           consultor.setPrecoDoServico(dto.getPrecoDoServico());
       }*/

       this.consultorRepository.save(consultor);
    }

    private void updateCliente(UsuarioDto dto, Cliente cliente){
        if (dto.getNome() != null && !dto.getNome().isEmpty()) {
            cliente.setNome(dto.getNome());
        }

        if (dto.getSenha() != null && !dto.getSenha().isEmpty()) {
            cliente.setSenha(passwordEncoder.encode(dto.getSenha()));
        }

        this.clienteRepository.save(cliente);
    }
}
