package com.Salvador.Sistema_gestion_notas.controller;


import com.Salvador.Sistema_gestion_notas.dto.request.AlumnoRequest;
import com.Salvador.Sistema_gestion_notas.dto.response.AlumnoResponse;
import com.Salvador.Sistema_gestion_notas.service.AlumnoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/alumnos")
@RequiredArgsConstructor
public class AlumnoController {
    private final AlumnoService alumnoService;

    @GetMapping
    public List<AlumnoResponse> listar(){
        return alumnoService.listar();
    }
    @GetMapping("/{id}")
    public AlumnoResponse obtener(@PathVariable Long id){
        return alumnoService.obtenerPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AlumnoResponse crear(@Valid @RequestBody AlumnoRequest request){
        return alumnoService.crear(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public  AlumnoResponse actualizar(@PathVariable Long id, @Valid @RequestBody AlumnoRequest request){
        return alumnoService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id){

        alumnoService.eliminar(id);
    }

}
