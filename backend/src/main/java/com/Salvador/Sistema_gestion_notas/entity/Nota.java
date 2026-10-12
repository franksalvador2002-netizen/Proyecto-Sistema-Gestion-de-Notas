package com.Salvador.Sistema_gestion_notas.entity;

import com.Salvador.Sistema_gestion_notas.enums.TipoEvaluacion;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "nota")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matricula_id", nullable = false)
    private Matricula matricula;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_evaluacion", nullable = false, length = 15)
    private TipoEvaluacion tipoEvaluacion;
     @Column(nullable = false, precision = 4, scale = 2)
     private BigDecimal valor;

     @Column(nullable = false, precision = 4, scale = 2)
     @Builder.Default
     private BigDecimal peso = BigDecimal.ONE;

     @Column(nullable = false)
     @Builder.Default
     private LocalDate fecha = LocalDate.now();

     @Column(length = 20)
     private  String observacion;

    @Column(name = "creado_en", updatable = false)
    private LocalDateTime creadoEn;

    @PrePersist
    void prePersist(){
        this.creadoEn = LocalDateTime.now();

    }

}
