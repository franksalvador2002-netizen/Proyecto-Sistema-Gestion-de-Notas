package com.Salvador.Sistema_gestion_notas.strategy;

import com.Salvador.Sistema_gestion_notas.entity.Nota;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/*
* Promedio aritmetico simple : suma notas/ cantidad de notas
*/
@Component("promedioSimple")
public class PromedioSimpleStrategy  implements  PromedioStrategy{
    @Override
    public BigDecimal calcular(List<Nota> notas) {
        // primero validamos que no sea null ni sea vacio
        if (notas == null || notas.isEmpty()){
            return BigDecimal.ZERO;
        }

        BigDecimal suma = notas.stream()
                .map(Nota::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return  suma.divide(BigDecimal.valueOf(notas.size()), 2 , RoundingMode.HALF_UP);

        /*
        * nota 1 = 18
        * nota 2 = 12
        * nota 3 = 14
        * 18+12+14 = 44/3
        * 14.66
        */
    }
}
