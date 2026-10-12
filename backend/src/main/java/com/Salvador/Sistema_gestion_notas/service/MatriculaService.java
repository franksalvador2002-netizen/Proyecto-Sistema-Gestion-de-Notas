package com.Salvador.Sistema_gestion_notas.service;

import com.Salvador.Sistema_gestion_notas.dto.request.MatriculaRequest;
import com.Salvador.Sistema_gestion_notas.dto.response.MatriculaResponse;
import com.Salvador.Sistema_gestion_notas.dto.response.ResumenAcademicoResponse;

import java.util.List;

public interface MatriculaService {
    List<MatriculaResponse> listar();
    MatriculaResponse obtenerPorId(Long id);
    MatriculaResponse crear(MatriculaRequest request);
    void eliminar(Long id);
    List<MatriculaResponse> listarPorAlumno(Long alumnoId);
    List<MatriculaResponse> listarPorCurso(Long cursoId);
    ResumenAcademicoResponse obtenerResumen(Long matriculaId);

}
