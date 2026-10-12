package com.Salvador.Sistema_gestion_notas.service.impl;

import com.Salvador.Sistema_gestion_notas.dto.request.NotaRequest;
import com.Salvador.Sistema_gestion_notas.dto.response.AlumnoResponse;
import com.Salvador.Sistema_gestion_notas.dto.response.NotaResponse;
import com.Salvador.Sistema_gestion_notas.entity.Alumno;
import com.Salvador.Sistema_gestion_notas.entity.Matricula;
import com.Salvador.Sistema_gestion_notas.entity.Nota;
import com.Salvador.Sistema_gestion_notas.exception.RecursoNoEncontradoException;
import com.Salvador.Sistema_gestion_notas.repository.MatriculaRepository;
import com.Salvador.Sistema_gestion_notas.repository.NotaRepository;
import com.Salvador.Sistema_gestion_notas.service.NotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class NotaServiceImpl implements NotaService {
    private final NotaRepository notaRepository;
    private final MatriculaRepository matriculaRepository;


    @Override
    public List<NotaResponse> listarPorMatricula(Long matriculaId) {
        return notaRepository.findByMatriculaId(matriculaId).stream().map(this::toResponse).toList();
    }

    @Override
    public NotaResponse crear(NotaRequest request) {

        Matricula matricula = matriculaRepository.findById(request.matriculaId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Matricula no encontrada con id" + request.matriculaId()));

        Nota nota = Nota.builder().matricula(matricula)
                .tipoEvaluacion(request.tipoEvaluacion())
                .valor(request.valor())
                .peso(request.peso() != null ? request.peso() : BigDecimal.ZERO)
                .fecha(request.fecha() != null ? request.fecha() : LocalDate.now())
                .observacion(request.observacion()).build();
        return toResponse(notaRepository.save(nota));
    }

    @Override
    public NotaResponse actualizar(Long id, NotaRequest request) {

        Nota nota = buscarEntidad(id);
        nota.setTipoEvaluacion(request.tipoEvaluacion());
        nota.setValor(request.valor());
        //validamos el peso y la fecha
        if (request.peso() != null) nota.setPeso(request.peso());
        if (request.fecha() != null) nota.setFecha(request.fecha());
        nota.setObservacion(request.observacion());

        return toResponse(notaRepository.save(nota));

    }

    @Override
    public void eliminar(Long id) {
        Nota nota = buscarEntidad(id);
        notaRepository.delete(nota);

    }

    public NotaResponse toResponse(Nota nota){
        return new NotaResponse(
                nota.getId(),
                nota.getMatricula().getId(),
                nota.getTipoEvaluacion().name(),
                nota.getValor(),
                nota.getPeso(),
                nota.getFecha(),
                nota.getObservacion()

        );
    }

    private Nota buscarEntidad(Long id){
        return notaRepository.findById(id).orElseThrow(() ->
                new RecursoNoEncontradoException("Nota no encontrado con el id" + id));
    }
}
