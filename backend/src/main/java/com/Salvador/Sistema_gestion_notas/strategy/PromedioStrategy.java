package com.Salvador.Sistema_gestion_notas.strategy;

import com.Salvador.Sistema_gestion_notas.entity.Nota;

import java.math.BigDecimal;
import java.util.List;


/*
* Estrategia de calculo de promedio (patron Strategy)
* Permite intercambiar la formula de promedio sin
* modificar el servicio que la usa
*
  */
public interface PromedioStrategy {
    BigDecimal calcular(List<Nota> notas);


}
