package br.edu.ifpe.tads.findmycar.repository;

import br.edu.ifpe.tads.findmycar.entity.Consultor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultorRepository extends JpaRepository<Consultor, Long> {
    @Query(value="SELECT c FROM Consultor c JOIN c.servicosBuscador sb WHERE sb.nome in :areas")
    List<Consultor> getBuscadores(@Param("areas") List<String> areas);

    @Query(value = "SELECT c FROM Consultor c JOIN c.locais l JOIN c.carroMarcas cm WHERE  l.uf = :locais AND cm.nome in :marcas")
    List<Consultor> getMecanicos(@Param("locais") String locais, @Param("marcas") List<String> marcas);
}
