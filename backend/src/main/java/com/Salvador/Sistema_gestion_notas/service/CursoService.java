package com.Salvador.Sistema_gestion_notas.service;

import com.Salvador.Sistema_gestion_notas.dto.request.CursoRequest;
import com.Salvador.Sistema_gestion_notas.dto.response.CursoResponse;

import java.util.List;

public interface CursoService {
    List<CursoResponse> listar();
    CursoResponse obtenerPorId(Long id);
    CursoResponse crear(CursoRequest request);
    CursoResponse actualizar(Long id, CursoRequest request);
    void eliminar(Long id);
}
