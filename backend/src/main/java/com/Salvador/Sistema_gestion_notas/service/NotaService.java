package com.Salvador.Sistema_gestion_notas.service;

import com.Salvador.Sistema_gestion_notas.dto.request.NotaRequest;
import com.Salvador.Sistema_gestion_notas.dto.response.NotaResponse;

import java.util.List;

public interface NotaService {
    List<NotaResponse> listarPorMatricula(Long matriculaId);
    NotaResponse crear(NotaRequest request);
    NotaResponse actualizar(Long id, NotaRequest request);
    void eliminar(Long id);

}
