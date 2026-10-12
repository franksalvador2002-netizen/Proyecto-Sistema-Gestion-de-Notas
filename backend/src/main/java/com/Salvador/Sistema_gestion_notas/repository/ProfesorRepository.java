package com.Salvador.Sistema_gestion_notas.repository;

import com.Salvador.Sistema_gestion_notas.entity.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfesorRepository extends JpaRepository<Profesor, Long> {
    Optional<Profesor> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByCodigo(String codigo);

}
