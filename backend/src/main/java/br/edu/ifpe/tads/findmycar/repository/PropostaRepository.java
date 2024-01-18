package br.edu.ifpe.tads.findmycar.repository;

import br.edu.ifpe.tads.findmycar.entity.Consultor;
import br.edu.ifpe.tads.findmycar.entity.Proposta;
import br.edu.ifpe.tads.findmycar.entity.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface PropostaRepository extends JpaRepository<Proposta, Long> {

    @Query(value="SELECT c FROM Proposta c JOIN c.cliente sb WHERE sb.id = :idcliente AND c.status = :status")
    List<Proposta> getPropostasCliente(@Param("idcliente") Long idcliente, @Param("status") Status status);
    @Query(value="SELECT c FROM Proposta c JOIN c.consultor sb WHERE sb.id = :idconsultor AND c.status = :status")
    List<Proposta> getPropostasConsultor(@Param("idconsultor") Long idconsultor, @Param("status") Status status);

    @Query(value="SELECT c FROM Proposta c JOIN c.cliente sb WHERE sb.id = :idcliente")
    List<Proposta> getPropostasClienteAll(@Param("idcliente") Long idcliente);
    @Query(value="SELECT c FROM Proposta c JOIN c.consultor sb WHERE sb.id = :idconsultor")
    List<Proposta> getPropostasConsultorAll(@Param("idconsultor") Long idconsultor);


}
