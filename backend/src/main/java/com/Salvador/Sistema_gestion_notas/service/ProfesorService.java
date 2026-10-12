package com.Salvador.Sistema_gestion_notas.service;

import com.Salvador.Sistema_gestion_notas.dto.request.ProfesorRequest;
import com.Salvador.Sistema_gestion_notas.dto.response.ProfesorResponse;

import java.util.List;

public interface ProfesorService {
    List<ProfesorResponse> listar();
    ProfesorResponse obtenerPorId(Long id);
    ProfesorResponse crear(ProfesorRequest request);
    ProfesorResponse actualizar(Long id, ProfesorRequest request);
    void eliminar(Long id);
}
