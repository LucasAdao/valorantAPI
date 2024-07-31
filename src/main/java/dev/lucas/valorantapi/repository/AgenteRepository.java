package dev.lucas.valorantapi.repository;

import dev.lucas.valorantapi.model.Agente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgenteRepository  extends JpaRepository<Agente, Long> {
}
