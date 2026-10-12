package com.Salvador.Sistema_gestion_notas.entity;


import com.Salvador.Sistema_gestion_notas.enums.EstadoMatricula;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "matricula")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Matricula {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alumno_id", nullable = false)
    private Alumno alumno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    @Column(name = "fecha_maricula", nullable = false)
    @Builder.Default
    private LocalDate fechaMatricula = LocalDate.now();


    @Column(name = "creado_en", updatable = false)
    private LocalDateTime creadoEn;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    @Builder.Default
    private EstadoMatricula estado = EstadoMatricula.ACTIVA;
    @PrePersist
    void prePersist(){
        this.creadoEn = LocalDateTime.now();
    }

}
