package br.com.fiap.pettech.pettech.exception;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {
    
    @ExceptionHandler(ControllerNotFoundException.class)
    @Operation(summary = "Tratamento de exceção de recurso não encontrado",
            description = "Manipula exceções quando um recurso não é encontrado",
            responses = {
                    @ApiResponse(description = "Recurso não encontrado",
                            responseCode = "404",
                            content = @Content(schema = @Schema(implementation = StandardError.class)))
            })
    public ResponseEntity<StandardError> entityNotFound(
            ControllerNotFoundException e,
            HttpServletRequest request) {
        
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError error = new StandardError();
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError("Entity not Found");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        
        return ResponseEntity.status(status).body(error);
    }
}
