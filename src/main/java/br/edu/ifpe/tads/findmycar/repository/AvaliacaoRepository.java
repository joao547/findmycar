package br.edu.ifpe.tads.findmycar.repository;

import br.edu.ifpe.tads.findmycar.entity.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
}
