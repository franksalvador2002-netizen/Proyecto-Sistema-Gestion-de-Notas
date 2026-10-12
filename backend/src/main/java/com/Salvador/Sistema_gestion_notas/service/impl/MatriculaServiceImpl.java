package com.Salvador.Sistema_gestion_notas.service.impl;

import com.Salvador.Sistema_gestion_notas.dto.request.MatriculaRequest;
import com.Salvador.Sistema_gestion_notas.dto.response.*;
import com.Salvador.Sistema_gestion_notas.entity.*;
import com.Salvador.Sistema_gestion_notas.enums.EstadoAsistencia;
import com.Salvador.Sistema_gestion_notas.exception.RecursoNoEncontradoException;
import com.Salvador.Sistema_gestion_notas.exception.ReglaNegocioException;
import com.Salvador.Sistema_gestion_notas.repository.*;
import com.Salvador.Sistema_gestion_notas.service.MatriculaService;
import com.Salvador.Sistema_gestion_notas.strategy.PromedioStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@Transactional

public class MatriculaServiceImpl  implements MatriculaService {

    private final MatriculaRepository matriculaRepository;
    private final AlumnoRepository alumnoRepository;
    private final CursoRepository cursoRepository;
    private final NotaRepository notaRepository;
    private final AsistenciaRepository asistenciaRepository;

    private final PromedioStrategy promedioSimple;
    private final PromedioStrategy promedioPonderado;

    public MatriculaServiceImpl(MatriculaRepository matriculaRepository,
                                AlumnoRepository alumnoRepository,
                                CursoRepository cursoRepository,
                                NotaRepository notaRepository,
                                AsistenciaRepository asistenciaRepository,
                               @Qualifier("promedioSimple") PromedioStrategy promedioSimple,
                               @Qualifier("promedioPonderado") PromedioStrategy promedioPonderado) {
        this.matriculaRepository = matriculaRepository;
        this.alumnoRepository = alumnoRepository;
        this.cursoRepository = cursoRepository;
        this.notaRepository = notaRepository;
        this.asistenciaRepository = asistenciaRepository;
        this.promedioSimple = promedioSimple;
        this.promedioPonderado = promedioPonderado;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MatriculaResponse> listar() {
        return matriculaRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public MatriculaResponse obtenerPorId(Long id) {
        return toResponse(buscarEntidad(id));
    }

    @Override
    public MatriculaResponse crear(MatriculaRequest request) {

        // validamos
        if (matriculaRepository.existsByAlumnoIdAndCursoId(request.alumnoId(),request.cursoId())){
            throw new ReglaNegocioException("El alumno a esta matriculado en este curso");
        }

        // buscamos el id alumno y el id curso
        Alumno alumno = alumnoRepository.findById(request.alumnoId())
                .orElseThrow(()-> new RecursoNoEncontradoException("Alumno no encontrado con id" + request.alumnoId()));

        Curso curso = cursoRepository.findById(request.cursoId())
                .orElseThrow(()-> new RecursoNoEncontradoException("Curso no encontrado con id" + request.cursoId()));

        Matricula matricula = Matricula.builder()
                .alumno(alumno)
                .curso(curso)
                .build();
        return toResponse(matriculaRepository.save(matricula));
    }

    @Override
    public void eliminar(Long id) {
            Matricula matricula = buscarEntidad(id);
            matriculaRepository.delete(matricula);

    }

    @Override
    public List<MatriculaResponse> listarPorAlumno(Long alumnoId) {

        return matriculaRepository.findByAlumnoId(alumnoId).stream().map(this::toResponse).toList();
    }

    @Override
    public List<MatriculaResponse> listarPorCurso(Long cursoId) {
        return matriculaRepository.findByCursoId(cursoId).stream().map(this::toResponse).toList();
    }

    @Override
    public ResumenAcademicoResponse obtenerResumen(Long matriculaId) {

        //buscamos el id de la matricula
        Matricula matricula = buscarEntidad(matriculaId);
        List<Nota> notas = notaRepository.findByMatriculaId(matriculaId);
        List<Asistencia> asistencias = asistenciaRepository.findByMatriculaId(matriculaId);

        BigDecimal pSimple = promedioSimple.calcular(notas);
        BigDecimal pPonderado = promedioPonderado.calcular(notas);

        // conteo de todas las asistencias
        long total = asistencias.size();
        //conteo de presente + tardanza
        long asistidas = asistencias.stream()
                .filter(asistio -> asistio.getEstado() == EstadoAsistencia.PRESENTE || asistio.getEstado() == EstadoAsistencia.TARDANZA)
                .count();

        // calcular las asistencias validas y las asistencias totales
        BigDecimal porcentaje = total == 0
                ? BigDecimal.ZERO
                : BigDecimal.valueOf(asistidas)
                .divide(BigDecimal.valueOf(total), 4, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100))
                .setScale(2,RoundingMode.HALF_UP);
        /*
        * Las asistencias validas va a ser divididas en asistencias totales y se va a multiplicar por cien
        */

        // obtener el porcentaje
        String nombrealumno = matricula.getAlumno().getNombres() +  " " + matricula.getAlumno().getApellidos();



        return new ResumenAcademicoResponse(matriculaId,nombrealumno,matricula.getCurso().getNombre(),pSimple,pPonderado,
                total,asistidas,porcentaje);
    }

    private Matricula buscarEntidad(Long id){
        return matriculaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Matricula no encontrada con id" + id));
    }

    private MatriculaResponse toResponse(Matricula matricula){
        Alumno alumno = matricula.getAlumno();
        Curso curso = matricula.getCurso();
        Profesor profesor = curso.getProfesor();
        AlumnoResponse alumnoResponse = new AlumnoResponse(
                alumno.getId(),
                alumno.getCodigo(),
                alumno.getNombres(),
                alumno.getApellidos(),
                alumno.getDni(),
                alumno.getEmail(),
                alumno.getFechaNacimiento(),
                alumno.getEstado().name()
        );

        ProfesorResponse profesorResponse = new ProfesorResponse(
                profesor.getId(),
                profesor.getCodigo(),
                profesor.getNombres(),
                profesor.getApellidos(),
                profesor.getEmail(),
                profesor.getEspecialidad(),
                profesor.getEstado().name()
        );

        CursoResponse cursoResponse = new CursoResponse(
                curso.getId(),
                curso.getCodigo(),
                curso.getNombre(),
                curso.getCreditos(),
                profesorResponse,
                curso.getEstado().name()

        );

        return new MatriculaResponse(matricula.getId(),
                alumnoResponse,
                cursoResponse,
                matricula.getFechaMatricula(),
                matricula.getEstado().name());
    }
}
