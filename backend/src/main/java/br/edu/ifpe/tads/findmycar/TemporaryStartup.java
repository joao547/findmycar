package br.edu.ifpe.tads.findmycar;

import br.edu.ifpe.tads.findmycar.entity.Cliente;
import br.edu.ifpe.tads.findmycar.entity.Consultor;
import br.edu.ifpe.tads.findmycar.repository.ClienteRepository;
import br.edu.ifpe.tads.findmycar.repository.ConsultorRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class TemporaryStartup {

    private final PasswordEncoder passwordEncoder;
    private final ClienteRepository clienteRepository;
    private final ConsultorRepository consultorRepository;

    public TemporaryStartup(PasswordEncoder passwordEncoder, ClienteRepository clienteRepository, ConsultorRepository consultorRepository) {
        this.passwordEncoder = passwordEncoder;
        this.clienteRepository = clienteRepository;
        this.consultorRepository = consultorRepository;
    }


   // @PostConstruct
    public void criarUsuarioInicial() {
        Cliente cliente = new Cliente();
        cliente.setEmail("cliente@email.com");
        cliente.setSenha(passwordEncoder.encode("1"));
        cliente.setNome("Cliente");
        clienteRepository.save(cliente);

        Consultor consultor = new Consultor();
        consultor.setEmail("consultor@email.com");
        consultor.setSenha(passwordEncoder.encode("1"));
        consultor.setNome("Consultor");
        consultor.setDisponibilidade("Disponivel");
        consultor.setAreaDeAtuacao("Recife");
        consultor.setPrecoDoServico(200.0);
        consultorRepository.save(consultor);
    }
}
