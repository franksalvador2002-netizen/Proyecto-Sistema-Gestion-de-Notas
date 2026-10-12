package com.Salvador.Sistema_gestion_notas.dto.response;

import java.time.LocalDate;

public record MatriculaResponse(
        Long id,
        AlumnoResponse alumno,
        CursoResponse curso,
        LocalDate fechaMatricula,
        String estado
) {
}
