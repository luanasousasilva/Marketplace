package com.marketplace.common;

import com.marketplace.application.shared.NotFoundException;
import com.marketplace.domain.shared.DomainException;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(NotFoundException.class) @ResponseStatus(HttpStatus.NOT_FOUND)
    ApiError notFound(NotFoundException ex) { return ApiError.of(404, ex.getMessage()); }
    @ExceptionHandler({DomainException.class, IllegalArgumentException.class}) @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    ApiError business(RuntimeException ex) { return ApiError.of(422, ex.getMessage()); }
    @ExceptionHandler(MethodArgumentNotValidException.class) @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiError validation(MethodArgumentNotValidException ex) { return new ApiError(Instant.now(), 400, "Dados inválidos", ex.getBindingResult().getFieldErrors().stream().collect(java.util.stream.Collectors.toMap(e -> e.getField(), e -> e.getDefaultMessage(), (a, b) -> a))); }
    record ApiError(Instant timestamp, int status, String message, Map<String, String> fields) { static ApiError of(int status, String message) { return new ApiError(Instant.now(), status, message, Map.of()); } }
}
