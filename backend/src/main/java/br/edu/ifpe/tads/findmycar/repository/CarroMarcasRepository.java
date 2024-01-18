package br.edu.ifpe.tads.findmycar.repository;

import br.edu.ifpe.tads.findmycar.entity.CarroMarcas;
import br.edu.ifpe.tads.findmycar.entity.Consultor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroMarcasRepository extends JpaRepository<CarroMarcas, Long> {

}
