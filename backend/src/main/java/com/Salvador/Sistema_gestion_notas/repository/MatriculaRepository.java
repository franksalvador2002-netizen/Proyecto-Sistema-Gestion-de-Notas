package com.Salvador.Sistema_gestion_notas.repository;

import com.Salvador.Sistema_gestion_notas.entity.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatriculaRepository  extends JpaRepository<Matricula, Long> {
    List<Matricula> findByAlumnoId(Long alumnoId);
    List<Matricula> findByCursoId(Long cursoId);
    boolean existsByAlumnoIdAndCursoId(Long alumnoId, Long cursoId);
}
