package com.Salvador.Sistema_gestion_notas.service.impl;

import com.Salvador.Sistema_gestion_notas.dto.request.ProfesorRequest;
import com.Salvador.Sistema_gestion_notas.dto.response.AlumnoResponse;
import com.Salvador.Sistema_gestion_notas.dto.response.ProfesorResponse;
import com.Salvador.Sistema_gestion_notas.entity.Alumno;
import com.Salvador.Sistema_gestion_notas.entity.Profesor;
import com.Salvador.Sistema_gestion_notas.exception.RecursoNoEncontradoException;
import com.Salvador.Sistema_gestion_notas.exception.ReglaNegocioException;
import com.Salvador.Sistema_gestion_notas.repository.ProfesorRepository;
import com.Salvador.Sistema_gestion_notas.service.ProfesorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProfesorServiceImpl implements ProfesorService {
    private final ProfesorRepository repository;



    @Override
    public List<ProfesorResponse> listar() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    public ProfesorResponse obtenerPorId(Long id) {
        return toResponse(buscarEntidad(id));
    }

    @Override
    public ProfesorResponse crear(ProfesorRequest request) {
        if (repository.existsByCodigo(request.codigo())){
            throw new ReglaNegocioException("Ya existe un profesor con el mismo codigo" + request.codigo());
        }

        if (repository.existsByEmail(request.email())){
            throw new ReglaNegocioException(("Ya existe un profesor con ese email" + request.email()));
        }

        Profesor profesor = Profesor.builder()
                .codigo(request.codigo())
                .nombres(request.nombres())
                .apellidos(request.apellidos())
                .email(request.email())
                .especialidad(request.especialidad())
                .build();

        return  toResponse(repository.save(profesor));
    }

    @Override
    public ProfesorResponse actualizar(Long id, ProfesorRequest request) {
        Profesor profesor = buscarEntidad(id);

        profesor.setNombres(request.nombres());
        profesor.setApellidos(request.apellidos());
        profesor.setEmail(request.email());
        profesor.setEspecialidad(request.especialidad());
        return toResponse(repository.save(profesor));
    }

    @Override
    public void eliminar(Long id) {
        Profesor profesor = buscarEntidad(id);
        repository.delete(profesor);

    }
    public ProfesorResponse toResponse(Profesor profesor){
        return new ProfesorResponse(
                profesor.getId(),
                profesor.getCodigo(),
                profesor.getNombres(),
                profesor.getApellidos(),
                profesor.getEmail(),
                profesor.getEspecialidad(),
                profesor.getEstado().name());
    }

    private Profesor buscarEntidad(Long id){
        return repository.findById(id).orElseThrow(() ->
                new RecursoNoEncontradoException("Profesor no encontrado con el id" + id));
    }
}
