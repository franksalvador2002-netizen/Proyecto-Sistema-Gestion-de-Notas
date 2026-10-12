package com.Salvador.Sistema_gestion_notas.repository;

import com.Salvador.Sistema_gestion_notas.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    List<Curso> findByProfesorId(Long profesorId);
    boolean existsByCodigo(String codigo);

}
