package com.Salvador.Sistema_gestion_notas.exception;

public class RecursoNoEncontradoException extends RuntimeException{

    // constructor con parametros
    public RecursoNoEncontradoException(String mensaje){
        ///  el super sirve heredar todos los atributos de su clase padre
        super(mensaje);

    }
}
