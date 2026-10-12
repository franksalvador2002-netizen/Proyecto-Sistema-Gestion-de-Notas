package com.Salvador.Sistema_gestion_notas.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CursoRequest(
        @NotBlank(message = "El codigo es obligatorio") String codigo,
        @NotBlank(message = "El nombre es obligatorio") String nombre,
        @Positive(message = "Los creditos deben ser positivos") Integer credito,
        @NotNull(message = "El profesor es obligatorio") Long profesorId

) {
}
