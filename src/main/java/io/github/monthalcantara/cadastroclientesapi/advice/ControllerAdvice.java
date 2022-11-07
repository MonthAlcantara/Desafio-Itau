package io.github.monthalcantara.cadastroclientesapi.advice;

import io.github.monthalcantara.cadastroclientesapi.exceptions.RecursoNaoEncontradoException;
import io.github.monthalcantara.cadastroclientesapi.exceptions.RegraNegocioException;
import io.github.monthalcantara.cadastroclientesapi.model.ErrosApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerAdvice {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<List<ErrosApi>> methodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        List<ErrosApi> collect = methodArgumentNotValidException.getBindingResult().getFieldErrors().stream().map(f -> new ErrosApi(f.getDefaultMessage(), f.getField(), Optional.ofNullable(f.getRejectedValue()).map(Objects::toString).orElse(""))).collect(Collectors.toList());
        return ResponseEntity.badRequest().body(collect);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> exception() {
        return ResponseEntity.internalServerError().body("Erro interno. Por favor contate a equipe responsável");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<ErrosApi> illegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity<>(transformError(e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrosApi> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>(transformError(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrosApi> handleConstraintViolationException() {
        final var mensagem = "Erro interno. Por favor contate a equipe responsável";
        return new ResponseEntity<>(transformError(mensagem), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrosApi> recursoNaoEncontradoException(RecursoNaoEncontradoException e) {
        return new ResponseEntity<>(transformError(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<ErrosApi> illegalStateException(IllegalStateException e) {
        return new ResponseEntity<>(transformError(e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<ErrosApi> regraNegocioException(RegraNegocioException e) {
        return new ResponseEntity<>(transformError(e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    private ErrosApi transformError(String message) {
        return new ErrosApi(List.of(message));
    }
}
