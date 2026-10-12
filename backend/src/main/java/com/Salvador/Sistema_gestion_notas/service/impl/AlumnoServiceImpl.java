package com.Salvador.Sistema_gestion_notas.service.impl;

import com.Salvador.Sistema_gestion_notas.dto.request.AlumnoRequest;
import com.Salvador.Sistema_gestion_notas.dto.response.AlumnoResponse;
import com.Salvador.Sistema_gestion_notas.entity.Alumno;
import com.Salvador.Sistema_gestion_notas.exception.RecursoNoEncontradoException;
import com.Salvador.Sistema_gestion_notas.exception.ReglaNegocioException;
import com.Salvador.Sistema_gestion_notas.repository.AlumnoRepository;
import com.Salvador.Sistema_gestion_notas.service.AlumnoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// para que esta clase cuando hacemos inyeccion de dependencia
// lo detecte como una clase especial y asi poder extraer sus metodos
@Service
@RequiredArgsConstructor // esta anotacion es para generar automaticamente un constructor
@Transactional // para poder modificar o no modificar nuestra base de datos
public class AlumnoServiceImpl implements AlumnoService {
    private final AlumnoRepository repository;

    @Override
    @Transactional(readOnly = true)// no modifica solo me trae informacion
    public List<AlumnoResponse> listar() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AlumnoResponse obtenerPorId(Long id) {
        return toResponse(buscarEntidad(id));
    }

    @Override
    public AlumnoResponse crear(AlumnoRequest request) {
        // si existe un alumno con el mismo codigo
        if (repository.existsByCodigo(request.codigo())){
            throw new ReglaNegocioException("Ya existe un alumno con el mismo codigo" + request.codigo());
        }
        if (repository.existsByDni(request.dni())){
            throw  new ReglaNegocioException(("Ya existe un alumno con el mismo dni" + request.dni()));
        }

        if (repository.existsByEmail(request.email())){
            throw new ReglaNegocioException(("Ya existe un alumno con ese email" + request.email()));
        }

        Alumno alumno = Alumno.builder().codigo(request.codigo()).
                nombres(request.nombres())
                .apellidos(request.apellidos())
                .dni(request.dni()).
                email(request.email()).
                fechaNacimiento(request.fechaNacimiento())
                .build();

        return  toResponse(repository.save(alumno));
    }

    @Override
    public AlumnoResponse actualizar(Long id, AlumnoRequest request) {
        // antes de actualizar tenemos que comprobar que exista ese id
        Alumno alumno = buscarEntidad(id);

        if (repository.existsByDniAndIdNot(request.dni(), id)){
           throw new ReglaNegocioException(("Ya existe otro alumno con el mismo dni" + request.dni()));
        }

        if (repository.existsByEmailAndIdNot(request.email(), id)){
            throw new ReglaNegocioException(("Ya existe otro alumno con ese email" + request.email()));
        }
        alumno.setCodigo(request.codigo());
        alumno.setNombres(request.nombres());
        alumno.setApellidos(request.apellidos());
        alumno.setEmail(request.email());
        alumno.setFechaNacimiento(request.fechaNacimiento());

        return toResponse(repository.save(alumno));
    }


    @Override
    public void eliminar(Long id) {
        Alumno alumno = buscarEntidad(id);
        repository.delete(alumno);


    }

    public AlumnoResponse toResponse(Alumno alumno){
        return new AlumnoResponse(
                alumno.getId(),
                alumno.getCodigo(),
                alumno.getNombres(),
                alumno.getApellidos(),
                alumno.getDni(),
                alumno.getEmail(),
                alumno.getFechaNacimiento(),
                alumno.getEstado().name()
        );
    }

    private Alumno buscarEntidad(Long id){
        return repository.findById(id).orElseThrow(() ->
                new RecursoNoEncontradoException("Alumno no encontrado con el id" + id));
    }
}
