package com.Salvador.Sistema_gestion_notas.dto.response;

import java.time.LocalDate;

public record AsistenciaResponse(
        Long id,
        Long matriculaId,
        LocalDate fecha,
        String estado,
        String observacion

) {
}
