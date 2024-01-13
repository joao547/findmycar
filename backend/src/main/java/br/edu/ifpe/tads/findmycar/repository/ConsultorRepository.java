package br.edu.ifpe.tads.findmycar.repository;

import br.edu.ifpe.tads.findmycar.entity.Consultor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultorRepository extends JpaRepository<Consultor, Long> {


    @Query(value ="SELECT c FROM Consultor c WHERE c.areasConsultor IN :areas")
    List<Consultor> findByAreasConsultants(@Param("areas") List<String> areas);
     //List<Consultor> findByAreasBuscadorIn(List<String> areas);
    @Query(value = "SELECT c FROM Consultor c JOIN c.locais l WHERE c.areasConsultor IN :areas AND l.ibgeCode IN:locais")
    List<Consultor> getSeekers(@Param("locais") List<Long> locais, @Param("areas") List<String> areaAtuacao);
}
