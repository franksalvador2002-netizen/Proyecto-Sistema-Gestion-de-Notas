package com.Salvador.Sistema_gestion_notas.controller;

import com.Salvador.Sistema_gestion_notas.dto.request.NotaRequest;
import com.Salvador.Sistema_gestion_notas.dto.request.ProfesorRequest;
import com.Salvador.Sistema_gestion_notas.dto.response.NotaResponse;
import com.Salvador.Sistema_gestion_notas.dto.response.ProfesorResponse;
import com.Salvador.Sistema_gestion_notas.service.ProfesorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/profesores")
@RequiredArgsConstructor
public class ProfesorController {

    private final ProfesorService profesorService;

    @GetMapping
    public List<ProfesorResponse> listarTodos(){
        return profesorService.listar();
    }

    @GetMapping("/{id}")
    public ProfesorResponse listarPorId(@PathVariable Long id){
        return profesorService.obtenerPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProfesorResponse crear(@Valid @RequestBody ProfesorRequest request){
        return profesorService.crear(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ProfesorResponse actualizar(@PathVariable Long id, @Valid @RequestBody ProfesorRequest request){
        return profesorService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id){

        profesorService.eliminar(id);
    }
}
