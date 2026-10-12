package com.Salvador.Sistema_gestion_notas.service.impl;

import com.Salvador.Sistema_gestion_notas.dto.request.AsistenciaRequest;
import com.Salvador.Sistema_gestion_notas.dto.response.AsistenciaResponse;
import com.Salvador.Sistema_gestion_notas.entity.Asistencia;
import com.Salvador.Sistema_gestion_notas.entity.Matricula;
import com.Salvador.Sistema_gestion_notas.exception.RecursoNoEncontradoException;
import com.Salvador.Sistema_gestion_notas.exception.ReglaNegocioException;
import com.Salvador.Sistema_gestion_notas.repository.AsistenciaRepository;
import com.Salvador.Sistema_gestion_notas.repository.MatriculaRepository;
import com.Salvador.Sistema_gestion_notas.service.AsistenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class AsistenciaServiceImpl implements AsistenciaService {
    private final AsistenciaRepository asistenciaRepository;
    private final MatriculaRepository matriculaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<AsistenciaResponse> listarPorMatricula(Long matriculaId) {
        return asistenciaRepository.findByMatriculaId(matriculaId).stream().map(this::toResponse).toList();
    }

    @Override
    public AsistenciaResponse crear(AsistenciaRequest request) {
        Matricula matricula = matriculaRepository.findById(request.matriculaId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Matricula no encontrada con id" + request.matriculaId()));
        // validamos si la fecha coincide que son las mismas
        if (asistenciaRepository.findByMatriculaAndFecha(request.matriculaId(),request.fecha()).isPresent()){
            throw new ReglaNegocioException("ya existe un registro de asistencia con la misma fecha");
        }

        Asistencia asistencia = Asistencia.builder()
                .matricula(matricula)
                .fecha(request.fecha())
                .estado(request.estado())
                .observacion(request.observacion()).build();

        return toResponse(asistenciaRepository.save(asistencia));
    }

    @Override
    public AsistenciaResponse actualizar(Long id, AsistenciaRequest request) {

        Asistencia asistencia = buscarEntidad(id);
        asistencia.setFecha(request.fecha());
        asistencia.setEstado(request.estado());
        asistencia.setObservacion(request.observacion());

        return toResponse(asistenciaRepository.save(asistencia));
    }

    @Override
    public void eliminar(Long id) {
        Asistencia asistencia = buscarEntidad(id);
        asistenciaRepository.delete(asistencia);

    }
    private Asistencia buscarEntidad(Long id){
        return asistenciaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Asistencia no encontrada por id" + id));
    }

    private AsistenciaResponse toResponse(Asistencia asistencia){
        return new AsistenciaResponse(
                asistencia.getId(),
                asistencia.getMatricula().getId(),
                asistencia.getFecha(),
                asistencia.getEstado().name(),
                asistencia.getObservacion()
        );
    }
}
