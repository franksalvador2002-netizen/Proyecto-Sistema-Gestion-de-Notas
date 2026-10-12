package com.Salvador.Sistema_gestion_notas.entity;

import com.Salvador.Sistema_gestion_notas.enums.EstadoRegistro;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "curso")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String codigo;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false)
    @Builder.Default // se da por defecto un valor a un atributo
    private Integer creditos = 3;
    // un curso y no va a cargar hasta que lo necesites eso seria lazy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profesor_id", nullable = false)
    private Profesor profesor;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    @Builder.Default
    private EstadoRegistro estado = EstadoRegistro.ACTIVO;

    @Column(name = "creado_en", updatable = false)
    private LocalDateTime creadoEN;

    @PrePersist
    void prePersist(){
        this.creadoEN = LocalDateTime.now();
    }


}
