package com.Salvador.Sistema_gestion_notas.exception;


import java.time.LocalDateTime;
import java.util.Map;

public record ErrorResponse(

        LocalDateTime tiempo_exacto,
        int estado,
        String error,
        String mensaje,
        Map<String, String> campos
) {
}
