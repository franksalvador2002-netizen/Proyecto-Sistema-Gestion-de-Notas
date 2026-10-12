package com.Salvador.Sistema_gestion_notas.entity;

import com.Salvador.Sistema_gestion_notas.enums.EstadoRegistro;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "alumno")
@Getter
@Setter
@AllArgsConstructor // para el constructor con todos los parametros
@NoArgsConstructor // para el constructor sin parametros
@Builder // para evitar los setters en otras clases

public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String codigo;

    @Column(nullable = false, length = 80)
    private String nombres;

    @Column(nullable = false, length = 20)
    private String apellidos;

    @Column(nullable = false, unique = true, length = 8)
    private String dni;

    @Column(nullable = false, length = 80)
    private String email;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    @Builder.Default
    private EstadoRegistro estado = EstadoRegistro.ACTIVO;

    // updatable = una vez creado no se va a actualizar
    @Column(name = "creado_en", updatable = false)
    private LocalDateTime creadoEn;

    // es una anotacion que se va a ejecutar antes de insertar una nueva entidad
    @PrePersist

    void prePersist(){
        this.creadoEn = LocalDateTime.now();
    }


}
