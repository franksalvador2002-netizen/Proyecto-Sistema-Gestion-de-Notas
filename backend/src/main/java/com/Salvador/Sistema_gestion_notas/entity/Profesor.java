package com.Salvador.Sistema_gestion_notas.entity;

import com.Salvador.Sistema_gestion_notas.enums.EstadoRegistro;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Entity
@Table(name = "profesor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String codigo;

    @Column(nullable = false, length = 80)
    private String nombres;
    @Column(nullable = false, length = 80)
    private String apellidos;
    @Column(nullable = false, unique = true,length = 120)
    private String email;

    @Column(nullable = false, length = 80)
    private String especialidad;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    @Builder.Default
    private EstadoRegistro estado = EstadoRegistro.ACTIVO;

    @Column(name = "creado_en", updatable = false)
    private LocalDateTime creadoEn;

    @PrePersist
    void prePersist(){
        this.creadoEn = LocalDateTime.now();

    }

}
