package com.Salvador.Sistema_gestion_notas.dto.response;

public record CursoResponse(
        Long id,
        String codigo,
        String nombre,
        Integer credito,
        ProfesorResponse profesor,
        String estado
) {
}
