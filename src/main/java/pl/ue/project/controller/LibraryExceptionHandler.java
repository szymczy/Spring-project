package pl.ue.project.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class LibraryExceptionHandler {
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<String> handleException(RuntimeException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}
