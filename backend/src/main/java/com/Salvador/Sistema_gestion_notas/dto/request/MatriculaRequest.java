package com.Salvador.Sistema_gestion_notas.dto.request;

import jakarta.validation.constraints.NotNull;

public record MatriculaRequest(
        @NotNull(message = "El alumno es obligatorio") Long alumnoId,
        @NotNull(message = "El curso es obligatorio") Long cursoId
) {
}
