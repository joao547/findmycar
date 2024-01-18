package br.edu.ifpe.tads.findmycar.repository;


import br.edu.ifpe.tads.findmycar.entity.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocalRepository extends JpaRepository<Local, Long> {
    Optional<Local> findByIbgeCode(Long ibgeCode);
}
