package com.example.Tarea_obligatoria.exception;

/**
 * Un producto del catálogo
 * @author Juan
 * @version Curso 2022-2023
 */
public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException() {
        super();
    }

    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(long id) {
        super("Product not found: " + id);
    }
}
