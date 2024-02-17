package br.com.aguaboaservicos.common.config;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ErrorsConfiguration {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> trataErroEntityNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErroValidBean>> trataErroNotValid(MethodArgumentNotValidException e) {

        var body = e.getFieldErrors().stream().map(ErroValidBean::new).toList();

        return ResponseEntity.badRequest().body(body);
    }

    private record ErroValidBean(String campo, String mensagem) {

        public ErroValidBean(FieldError e) {
            this(e.getField(), e.getDefaultMessage());
        }
    }
}
