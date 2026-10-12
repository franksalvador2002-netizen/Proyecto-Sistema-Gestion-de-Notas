package com.Salvador.Sistema_gestion_notas.dto.request;

import com.Salvador.Sistema_gestion_notas.enums.EstadoAsistencia;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

// not null solo valida  que no sea null
public record AsistenciaRequest(
        @NotNull(message = " la matricula es obligatoria") Long matriculaId,
        @NotNull(message = "La fecha es obligatoria") LocalDate fecha,
        @NotNull(message = "El esta es obligatorio")EstadoAsistencia estado,
        String observacion
        ) {
}
