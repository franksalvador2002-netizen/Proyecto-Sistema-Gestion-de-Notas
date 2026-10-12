package com.Salvador.Sistema_gestion_notas.controller;

import com.Salvador.Sistema_gestion_notas.dto.request.AlumnoRequest;
import com.Salvador.Sistema_gestion_notas.dto.request.MatriculaRequest;
import com.Salvador.Sistema_gestion_notas.dto.response.AlumnoResponse;
import com.Salvador.Sistema_gestion_notas.dto.response.MatriculaResponse;
import com.Salvador.Sistema_gestion_notas.dto.response.ResumenAcademicoResponse;
import com.Salvador.Sistema_gestion_notas.service.MatriculaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/matriculas")
@RequiredArgsConstructor
public class MatriculaController {
         private final MatriculaService matriculaService;

    @GetMapping
    public List<MatriculaResponse> listar(){
        return matriculaService.listar();
    }
    @GetMapping("/{id}")
    public MatriculaResponse obtener(@PathVariable Long id){
        return matriculaService.obtenerPorId(id);
    }
    @GetMapping("/alumno/alumnoId")
    public List<MatriculaResponse> listarporAlumno(@PathVariable Long id){
        return matriculaService.listarPorAlumno(id);
    }
    @GetMapping("/curso/cursoId")
    public List<MatriculaResponse> listarporCurso(@PathVariable Long id){
        return matriculaService.listarPorCurso(id);
    }
    @GetMapping("/{id}/resumen")
    public ResumenAcademicoResponse obtenerResumen(@PathVariable Long id){
        return matriculaService.obtenerResumen(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MatriculaResponse crear(@Valid @RequestBody MatriculaRequest request){
        return matriculaService.crear(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id){
        matriculaService.eliminar(id);
    }
}
