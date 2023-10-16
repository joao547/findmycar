package br.edu.ifpe.tads.findmycar.repository;

import br.edu.ifpe.tads.findmycar.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findUsuarioByEmail(String email);
}
