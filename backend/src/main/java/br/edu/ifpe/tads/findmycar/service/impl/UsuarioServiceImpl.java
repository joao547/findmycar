package br.edu.ifpe.tads.findmycar.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import br.edu.ifpe.tads.findmycar.entity.*;
import br.edu.ifpe.tads.findmycar.repository.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.edu.ifpe.tads.findmycar.controller.exceptions.BadRequestException;
import br.edu.ifpe.tads.findmycar.dto.UsuarioDTOInfo;
import br.edu.ifpe.tads.findmycar.dto.UsuarioDto;
import br.edu.ifpe.tads.findmycar.enums.TipoUsuario;
import br.edu.ifpe.tads.findmycar.infra.security.JWTUtil;
import br.edu.ifpe.tads.findmycar.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
  private final ConsultorRepository consultorRepository;
  private final ClienteRepository clienteRepository;
  private final PasswordEncoder passwordEncoder;
  private final UsuarioRepository usuarioRepository;
  private final LocalRepository localRepository;
  private final CarroMarcasRepository carroMarcasRepository;
  private final ServicosBuscadorRepository servicosBuscadorRepository;
  private final JWTUtil jwtUtil;

  @Value("${file.upload-dir}")
  private String uploadDir;

  public UsuarioServiceImpl(
      ConsultorRepository consultorRepository,
      ClienteRepository clienteRepository,
      PasswordEncoder passwordEncoder,
      UsuarioRepository usuarioRepository,
      LocalRepository localRepository,
      CarroMarcasRepository carroMarcasRepository,
      ServicosBuscadorRepository servicosBuscadorRepository,
      JWTUtil jwtUtil
  ) {
    this.consultorRepository = consultorRepository;
    this.clienteRepository = clienteRepository;
    this.passwordEncoder = passwordEncoder;
    this.usuarioRepository = usuarioRepository;
    this.localRepository = localRepository;
    this.carroMarcasRepository = carroMarcasRepository;
    this.servicosBuscadorRepository = servicosBuscadorRepository;
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
//      usuarioDTOInfo = new UsuarioDTOInfo(
//          consultor.getId(),
//          consultor.getNome(),
//          consultor.getEmail(),
//          consultor.getPrecoDoServico(),
//          consultor.getAreaDeAtuacao(),
//          "CONSULTOR",
//          this.recuperarArquivo(consultor.getFotoPerfil())
//      );

      return Optional.empty();
    }

    if (clienteOptional.isPresent()) {
      Cliente cliente = clienteOptional.get();
      usuarioDTOInfo = new UsuarioDTOInfo(
          cliente.getId(),
          cliente.getNome(),
          cliente.getEmail(),
          "CLIENTE",
          this.recuperarArquivo(cliente.getFotoPerfil())
      );

      return Optional.of(usuarioDTOInfo);
    }

    return Optional.empty();
  }

  @Override
  public void criarUsuario(UsuarioDto dto, MultipartFile file) throws BadRequestException {
    String fileLocation = salvarArquivo(file);
    System.out.println(fileLocation);

    if (TipoUsuario.CONSULTOR.equals(dto.getTipo())) {
      this.consultorRepository.save(criarConsultor(dto, fileLocation));
    } else if (TipoUsuario.CLIENTE.equals(dto.getTipo())) {
      this.clienteRepository.save(criarCliente(dto, fileLocation));
    } else {
      throw new BadRequestException("Tipo de usuário não pode ser aceito");
    }
  }

  private String getFileExtension(String fileName) {
    String[] extension = fileName.split("/.");

    return extension[0];
  }

  private String salvarArquivo(MultipartFile file) {
    try {
      long time = System.currentTimeMillis();
      String fileName = String.format("%s%s", time, getFileExtension(file.getOriginalFilename()));
      Path filePath = Path.of(uploadDir, fileName);
      Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
      return time + file.getOriginalFilename();
    } catch (IOException e) {
      return "";
    }
  }

  private String recuperarArquivo(String fileName) {
    try {
      Path filePath = Path.of(uploadDir, fileName);
      byte[] fileContent = Files.readAllBytes(filePath);
      return java.util.Base64.getEncoder().encodeToString(fileContent);
    } catch (Exception e) {
      System.out.printf("Erro ao buscar o arquivo de nome [%s]", fileName);
      return "";
    }
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



  private Consultor criarConsultor(UsuarioDto dto, String file) {
    Consultor consultor = new Consultor();
    consultor.setEmail(dto.getEmail());
    consultor.setNome(dto.getNome());
    consultor.setSenha(passwordEncoder.encode(dto.getSenha()));
    if(file != null)consultor.setFotoPerfil(file);
    consultor.setLocais(findLocais(dto.getLocais()));
    consultor.setCarroMarcas(findCarroMarcas(dto.getCarroMarcas()));
    consultor.setServicosBuscador(findServicosBuscador(dto.getServicosBuscador()));
    consultor.setPrecoServicoBuscador(dto.getPrecoServicoBuscador());
    consultor.setPrecoServicoMecanico(dto.getPrecoServicoMecanico());

    return consultor;
  }


  private Cliente criarCliente(UsuarioDto dto, String file) {
    Cliente cliente = new Cliente();
    cliente.setEmail(dto.getEmail());
    cliente.setNome(dto.getNome());
    cliente.setSenha(passwordEncoder.encode(dto.getSenha()));
    cliente.setFotoPerfil(file);

    return cliente;
  }

  private Set<Local> findLocais(Set<Local> locais) {
    Set<Local> locaisBase = new HashSet<>();

    for (Local local : locais) {
      locaisBase.add(localRepository.findByIbgeCode(local.getIbgeCode()).orElse(local));
    }

    return locaisBase;
  }

  private Set<CarroMarcas> findCarroMarcas(Set<CarroMarcas> marcas){
    Set<CarroMarcas> marcasBase = new HashSet<>();

    for(CarroMarcas marca: marcas){
      marcasBase.add(carroMarcasRepository.findById(marca.getId()).orElse(marca));
    }
    return marcasBase;
  }

  private Set<ServicosBuscador> findServicosBuscador(Set<ServicosBuscador> servicos){
    Set<ServicosBuscador> marcasBase = new HashSet<>();

    for(ServicosBuscador servico: servicos){
      marcasBase.add(servicosBuscadorRepository.findById(servico.getId()).orElse(servico));
    }
    return marcasBase;
  }

}
