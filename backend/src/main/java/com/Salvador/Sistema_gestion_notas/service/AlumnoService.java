package com.Salvador.Sistema_gestion_notas.service;

import com.Salvador.Sistema_gestion_notas.dto.request.AlumnoRequest;
import com.Salvador.Sistema_gestion_notas.dto.response.AlumnoResponse;

import java.util.List;

public interface AlumnoService {
    List<AlumnoResponse> listar();
    AlumnoResponse obtenerPorId(Long id);
    AlumnoResponse crear(AlumnoRequest request);
    AlumnoResponse actualizar(Long id,AlumnoRequest request);
    void eliminar(Long id);
}
