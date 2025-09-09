package com.BeneLoan.BeneLoan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequest(BadRequestException badRequestException)
    {
        Map<String, Object> body = new HashMap<>();
/*        body.put("errors ", badRequestException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", badRequestException.getMessage(),
                "errors", badRequestException.getErrors()));*/

        body.put("message", badRequestException.getMessage());            // "CSV VALIDATION FAILED"
        body.put("errors", badRequestException.getErrors());              // <-- detailed per-row errors
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }


}
