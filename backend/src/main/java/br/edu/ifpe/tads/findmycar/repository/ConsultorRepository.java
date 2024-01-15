package br.edu.ifpe.tads.findmycar.repository;

import br.edu.ifpe.tads.findmycar.entity.Consultor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultorRepository extends JpaRepository<Consultor, Long> {
    @Query(
        nativeQuery = true,
        value="SELECT c FROM usuario c INNER JOIN user_list_consultant ab ON c.id = ab.consultor_id WHERE ab.areas_consultor IN :areas"
    )
    List<Consultor> getBuscadores(@Param("areas") List<String> areas);
    @Query(value = "SELECT c FROM Consultor c INNER JOIN c.locais l WHERE  l.uf = :locais")
    List<Consultor> getMecanicos(@Param("locais") String locais);
}
