package com.Salvador.Sistema_gestion_notas.controller;

import com.Salvador.Sistema_gestion_notas.dto.request.NotaRequest;
import com.Salvador.Sistema_gestion_notas.dto.response.NotaResponse;
import com.Salvador.Sistema_gestion_notas.entity.Nota;
import com.Salvador.Sistema_gestion_notas.service.NotaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/notas")
@RequiredArgsConstructor
public class NotaController {

    private final NotaService notaService;

    @GetMapping("matricula/matriculaId")
    public List<NotaResponse> listarPorMatricula(@PathVariable Long matriculaId){
        return notaService.listarPorMatricula(matriculaId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NotaResponse crear(@Valid @RequestBody NotaRequest request){
        return notaService.crear(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public NotaResponse actualizar(@PathVariable Long id, @Valid @RequestBody NotaRequest request){
        return notaService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id){

        notaService.eliminar(id);
    }

}
