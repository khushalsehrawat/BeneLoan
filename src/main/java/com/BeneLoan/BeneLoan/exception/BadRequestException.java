package com.BeneLoan.BeneLoan.exception;

import com.BeneLoan.BeneLoan.dto.RowError;

import java.util.List;

public class BadRequestException extends RuntimeException{

    private final List<RowError> errors;

    public BadRequestException(List<RowError> errors){
        super("CSV VALIDATION FAILED");
        this.errors = errors;
    }

    public List<RowError> getErrors() {
        return errors;
    }

}
