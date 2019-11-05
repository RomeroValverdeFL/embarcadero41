package pe.upn.embarcadero.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import pe.upn.embarcadero.model.entity.Plato;

@Repository
public interface PlatosRepository extends JpaRepository<Plato, Integer>{

	
}
