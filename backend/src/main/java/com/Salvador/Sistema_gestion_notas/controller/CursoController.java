package com.Salvador.Sistema_gestion_notas.controller;

import com.Salvador.Sistema_gestion_notas.dto.request.AlumnoRequest;
import com.Salvador.Sistema_gestion_notas.dto.request.CursoRequest;
import com.Salvador.Sistema_gestion_notas.dto.response.AlumnoResponse;
import com.Salvador.Sistema_gestion_notas.dto.response.CursoResponse;
import com.Salvador.Sistema_gestion_notas.service.CursoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService cursoService;

    @GetMapping
    public List<CursoResponse> listar(){
        return cursoService.listar();
    }
    @GetMapping("/{id}")
    public CursoResponse obtener(@PathVariable Long id){
        return cursoService.obtenerPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CursoResponse crear(@Valid @RequestBody CursoRequest request){
        return cursoService.crear(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public  CursoResponse actualizar(@PathVariable Long id, @Valid @RequestBody CursoRequest request){
        return cursoService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id){

        cursoService.eliminar(id);
    }


}
