package br.edu.ifpe.tads.findmycar;

import br.edu.ifpe.tads.findmycar.entity.Usuario;
import br.edu.ifpe.tads.findmycar.repository.UsuarioRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class TemporaryStartup {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;

    public TemporaryStartup(PasswordEncoder passwordEncoder, UsuarioRepository usuarioRepository) {
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
    }

    @PostConstruct
    public void criarUsuarioInicial() {
        Usuario usuario = new Usuario();
        usuario.setEmail("fulano@email.com");
        usuario.setSenha(passwordEncoder.encode("lala123"));

        usuarioRepository.save(usuario);
    }
}
