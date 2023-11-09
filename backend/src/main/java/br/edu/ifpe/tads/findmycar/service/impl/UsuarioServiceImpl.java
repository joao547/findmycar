package br.edu.ifpe.tads.findmycar.service.impl;

import br.edu.ifpe.tads.findmycar.controller.exceptions.BadRequestException;
import br.edu.ifpe.tads.findmycar.dto.UsuarioDTOInfo;
import br.edu.ifpe.tads.findmycar.dto.UsuarioDto;
import br.edu.ifpe.tads.findmycar.entity.Cliente;
import br.edu.ifpe.tads.findmycar.entity.Consultor;
import br.edu.ifpe.tads.findmycar.entity.Usuario;
import br.edu.ifpe.tads.findmycar.enums.TipoUsuario;
import br.edu.ifpe.tads.findmycar.infra.security.JWTUtil;
import br.edu.ifpe.tads.findmycar.repository.ClienteRepository;
import br.edu.ifpe.tads.findmycar.repository.ConsultorRepository;
import br.edu.ifpe.tads.findmycar.repository.UsuarioRepository;
import br.edu.ifpe.tads.findmycar.service.UsuarioService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final ConsultorRepository consultorRepository;
    private final ClienteRepository clienteRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;
    private final JWTUtil jwtUtil;

    public UsuarioServiceImpl(ConsultorRepository consultorRepository, ClienteRepository clienteRepository, PasswordEncoder passwordEncoder, UsuarioRepository usuarioRepository, JWTUtil jwtUtil) {
        this.consultorRepository = consultorRepository;
        this.clienteRepository = clienteRepository;
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Optional<Usuario> findUsuarioByEmail(String email) {
        return this.usuarioRepository.findUsuarioByEmail(email);
    }

    @Override
    public Optional<UsuarioDTOInfo> findUsuarioByJWT(String jwt) {
        UsuarioDTOInfo usuarioDTOInfo;
        String email = jwtUtil.getEmailFromToken(jwt);

        Optional<Usuario> usuario = this.usuarioRepository.findUsuarioByEmail(email);
        if (usuario.isEmpty())
            return Optional.empty();

        Optional<Consultor> consultorOptional = this.consultorRepository.findById(usuario.get().getId());
        Optional<Cliente> clienteOptional = this.clienteRepository.findById(usuario.get().getId());

        if (consultorOptional.isPresent()) {
            Consultor consultor = consultorOptional.get();
            usuarioDTOInfo = new UsuarioDTOInfo(
                consultor.getId(),
                consultor.getNome(),
                consultor.getEmail(),
                consultor.getPrecoDoServico(),
                consultor.getAreaDeAtuacao(),
                "CONSULTOR"
            );

            return Optional.of(usuarioDTOInfo);
        }

        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            usuarioDTOInfo = new UsuarioDTOInfo(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                "CLIENTE"
            );

            return Optional.of(usuarioDTOInfo);
        }

        return Optional.empty();
    }

    @Override
    public void criarUsuario(UsuarioDto dto) throws BadRequestException {
        if (TipoUsuario.CONSULTOR.equals(dto.getTipo())){
            this.consultorRepository.save(criarConsultor(dto));
        }
        else if(TipoUsuario.CLIENTE.equals(dto.getTipo())){
            this.clienteRepository.save(criarCliente(dto));
        }
        else {
            throw new BadRequestException("Tipo de usuário não pode ser aceito");
        }
    }

    @Override
    public void atualizarUsuario(UsuarioDto dto, String tokenJWT) {
        String email = this.jwtUtil.getEmailFromToken(tokenJWT);
        Optional<Usuario> usuario = this.usuarioRepository.findUsuarioByEmail(email);

        if (usuario.isEmpty())
            throw new UsernameNotFoundException("User not found");

        if (dto.getTipo().getName().equalsIgnoreCase("CONSULTOR")) {
            Consultor consultor = criarConsultor(dto);
            consultor.setId(usuario.get().getId());
            this.consultorRepository.save(consultor);
        }

        if (dto.getTipo().getName().equalsIgnoreCase("CLIENTE")) {
            Cliente cliente = criarCliente(dto);
            cliente.setId(usuario.get().getId());
            this.clienteRepository.save(cliente);
        }
    }

    private Consultor criarConsultor(UsuarioDto dto){
        Consultor consultor = new Consultor();
        consultor.setEmail(dto.getEmail());
        consultor.setNome(dto.getNome());
        consultor.setSenha(passwordEncoder.encode(dto.getSenha()));
        consultor.setDisponibilidade(dto.getDisponibilidade());
        consultor.setAreaDeAtuacao(dto.getAreaDeAtuacao());
        consultor.setPrecoDoServico(dto.getPrecoDoServico());

        return consultor;
    }

    private Cliente criarCliente(UsuarioDto dto){
        Cliente cliente = new Cliente();
        cliente.setEmail(dto.getEmail());
        cliente.setNome(dto.getNome());
        cliente.setSenha(passwordEncoder.encode(dto.getSenha()));

        return cliente;

    }
}
