package com.Salvador.Sistema_gestion_notas.repository;

import com.Salvador.Sistema_gestion_notas.entity.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {
    List<Asistencia> findByMatriculaId(Long matriculaId);
    Optional<Asistencia> findByMatriculaAndFecha(Long matriculaId, LocalDate fecha);

}
