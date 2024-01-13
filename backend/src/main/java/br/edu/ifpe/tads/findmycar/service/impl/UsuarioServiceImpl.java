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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public void criarUsuario(UsuarioDto dto, MultipartFile file) throws BadRequestException {
        if (TipoUsuario.CONSULTOR.equals(dto.getTipo())){
            this.consultorRepository.save(criarConsultor(dto, file));
        }
        else if(TipoUsuario.CLIENTE.equals(dto.getTipo())){
            this.clienteRepository.save(criarCliente(dto, file));
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
            Consultor consultor = criarConsultor(dto, null);
            consultor.setId(usuario.get().getId());
            this.consultorRepository.save(consultor);
        }

        if (dto.getTipo().getName().equalsIgnoreCase("CLIENTE")) {
            Cliente cliente = criarCliente(dto, null);
            cliente.setId(usuario.get().getId());
            this.clienteRepository.save(cliente);
        }
    }

    private Consultor criarConsultor(UsuarioDto dto, MultipartFile file){
        Consultor consultor = new Consultor();
        consultor.setEmail(dto.getEmail());
        consultor.setNome(dto.getNome());
        consultor.setSenha(passwordEncoder.encode(dto.getSenha()));
        //consultor.setDisponibilidade(dto.getDisponibilidade());
        //consultor.setAreaDeAtuacao(dto.getAreaDeAtuacao());
        //consultor.setPrecoDoServico(dto.getPrecoDoServico());
        consultor.setAreasConsultor(dto.getAreasConsultor());
        consultor.setAreasBuscador(dto.getAreasBuscador());
        consultor.setLocais(dto.getLocais());
        try {
            if(!file.isEmpty()){
            consultor.setFotoPerfil(file.getBytes());}
        } catch (IOException e) {
            throw new RuntimeException("erro ao salvar a foto: " + e);
        }
        return consultor;
    }

    private Cliente criarCliente(UsuarioDto dto, MultipartFile file){
        Cliente cliente = new Cliente();
        cliente.setEmail(dto.getEmail());
        cliente.setNome(dto.getNome());
        cliente.setSenha(passwordEncoder.encode(dto.getSenha()));
        try {
            if(!file.isEmpty()){
            cliente.setFotoPerfil(file.getBytes());}
        } catch (IOException e) {
            throw new RuntimeException("erro ao salvar a foto: " + e);
        }

        return cliente;
    }
    public void uploadFotoPerfil(Long consultorId, MultipartFile file) {
        Consultor consultor = consultorRepository.findById(consultorId)
                .orElseThrow(() -> new RuntimeException ("não foi possível encontrar o Consultor"));

        try {
            consultor.setFotoPerfil(file.getBytes());
            consultorRepository.save(consultor);
        } catch (IOException e) {
            throw new RuntimeException("erro ao salvar a foto: " + e);
        }
    }

    public byte[] downloadFotoPerfil(Long consultorId) {
        Consultor consultor = consultorRepository.findById(consultorId)
                .orElseThrow(() -> new RuntimeException("não foi possível buscar a foto"));

        return consultor.getFotoPerfil();
    }

}
