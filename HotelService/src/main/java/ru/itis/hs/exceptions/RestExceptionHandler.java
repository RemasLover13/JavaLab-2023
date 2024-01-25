package ru.itis.hs.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.itis.hs.dto.StandardResponseDto;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(RestException.class)
    public ResponseEntity<StandardResponseDto> handleRestException(RestException ex) {
            return ResponseEntity
                    .status(ex.getStatus())
                    .body(StandardResponseDto
                            .builder()
                            .message(ex.getMessage())
                            .build());
    }
}
