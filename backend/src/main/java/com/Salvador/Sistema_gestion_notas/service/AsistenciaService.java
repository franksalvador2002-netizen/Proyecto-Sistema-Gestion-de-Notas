package com.Salvador.Sistema_gestion_notas.service;

import com.Salvador.Sistema_gestion_notas.dto.request.AsistenciaRequest;
import com.Salvador.Sistema_gestion_notas.dto.response.AsistenciaResponse;

import java.util.List;

public interface AsistenciaService {
    List<AsistenciaResponse> listarPorMatricula(Long matriculaId);
    AsistenciaResponse crear(AsistenciaRequest request);
    AsistenciaResponse actualizar(Long id, AsistenciaRequest request);
    void eliminar(Long id);
}
