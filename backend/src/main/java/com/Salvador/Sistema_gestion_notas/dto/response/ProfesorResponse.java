package com.Salvador.Sistema_gestion_notas.dto.response;

public record ProfesorResponse(
        Long id,
        String codigo,
        String nombres,
        String apellidos,
        String email,
        String especialidad,
        String estado
) {
}
