package br.edu.ifpe.tads.findmycar.repository;

import br.edu.ifpe.tads.findmycar.entity.Avaliacao;
import br.edu.ifpe.tads.findmycar.entity.Consultor;
import br.edu.ifpe.tads.findmycar.entity.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    @Query(value="SELECT c FROM Avaliacao c  WHERE c.avaliado = :consultor")
    List<Avaliacao> getAvaliacoesConsultorAll(@Param("consultor") Consultor consultor);
}
