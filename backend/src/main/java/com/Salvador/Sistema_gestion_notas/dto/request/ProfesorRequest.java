package com.Salvador.Sistema_gestion_notas.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ProfesorRequest(
        @NotBlank(message = "El codigo es obligatorio") String codigo,
        @NotBlank(message = "Los nombres son obligatorios") String nombres,
        @NotBlank(message = "Los apellidos son obligatorios") String apellidos,
        @NotBlank(message = "El email es obligatorio") @Email(message = "El email no es valido") String email,
        String especialidad
) {
}
