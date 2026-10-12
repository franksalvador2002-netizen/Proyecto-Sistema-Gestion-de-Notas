package com.Salvador.Sistema_gestion_notas.dto.request;

import com.Salvador.Sistema_gestion_notas.enums.TipoEvaluacion;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record NotaRequest(
        @NotNull(message = "La matricula es obligatoria") Long matriculaId,
        @NotNull(message = "El tipo de evaluacion es obligatorio")TipoEvaluacion tipoEvaluacion,
        @NotNull(message = "El valor es obligatorio ")
        @DecimalMin(value = "o.o", message = "La nota minima es 0")
        @DecimalMax(value = "20.0", message = "La nota maxima es 20")
        BigDecimal valor,
        @DecimalMin(value = "0.0", message = "El peso no puede ser negativo") BigDecimal peso,
        LocalDate fecha,
        String observacion
        ) {
}
