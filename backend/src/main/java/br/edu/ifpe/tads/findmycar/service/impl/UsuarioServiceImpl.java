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
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private static final Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);
    private final ConsultorRepository consultorRepository;
    private final ClienteRepository clienteRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;
    private final JWTUtil jwtUtil;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public UsuarioServiceImpl(ConsultorRepository consultorRepository, ClienteRepository clienteRepository, PasswordEncoder passwordEncoder, UsuarioRepository usuarioRepository, JWTUtil jwtUtil) {
        this.consultorRepository = consultorRepository;
        this.clienteRepository = clienteRepository;
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Optional<Usuario> findUsuarioByEmail(String email) {
        return this.usuarioRepository.findByEmail(email);
    }

    @Override
    public Optional<UsuarioDTOInfo> findUsuarioByJWT(String jwt) {
        UsuarioDTOInfo usuarioDTOInfo;
        String email = jwtUtil.getEmailFromToken(jwt);

        Optional<Usuario> usuario = this.usuarioRepository.findByEmail(email);
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
        String fileLocation = salvarArquivo(file);
        if (TipoUsuario.CONSULTOR.equals(dto.getTipo())){
            this.consultorRepository.save(criarConsultor(dto, fileLocation));
        }
        else if(TipoUsuario.CLIENTE.equals(dto.getTipo())){
            this.clienteRepository.save(criarCliente(dto, fileLocation));
        }
        else {
            throw new BadRequestException("Tipo de usuário não pode ser aceito");
        }
    }

    private String salvarArquivo(MultipartFile file) {
        try {
            Path filePath = Path.of(uploadDir, file.getOriginalFilename());
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            byte[] fileContent = Files.readAllBytes(filePath);
            return java.util.Base64.getEncoder().encodeToString(fileContent);
        }catch (IOException e) {
            return "";
        }
    }

    private String convertFileToBase64(MultipartFile file) {
        String fileBase64 = "";
        if (file == null) {
            return "";
        }

        if (file.isEmpty()) {
            return "";
        }

        try{
            byte[] image = Base64.encodeBase64(file.getBytes(), false);
            fileBase64 = new String(image);
        } catch(Exception e) {
            logger.error(e.getMessage());
        }

        return fileBase64;
    }

    @Override
    public void atualizarUsuario(UsuarioDto dto, String tokenJWT) {
        String email = this.jwtUtil.getEmailFromToken(tokenJWT);
        Optional<Usuario> usuario = this.usuarioRepository.findByEmail(email);

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

    private Consultor criarConsultor(UsuarioDto dto, String file){
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
        consultor.setFotoPerfil(file);

        return consultor;
    }

    private Cliente criarCliente(UsuarioDto dto, String file){
        Cliente cliente = new Cliente();
        cliente.setEmail(dto.getEmail());
        cliente.setNome(dto.getNome());
        cliente.setSenha(passwordEncoder.encode(dto.getSenha()));
        cliente.setFotoPerfil(file);

        return cliente;
    }
}
