package com.db.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.*;

/**
 * Created by mmaykarkar on 12/02/17.
 */
@ControllerAdvice
public class GenericExceptionHandler {

    @ExceptionHandler(InvalidFieldException.class)
    public ResponseEntity<ErrorResponse> handleInvalidFieldException(HttpServletRequest req, InvalidFieldException ex) {
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
        error.setField(ex.getField());
        error.setMessage("Please enter valid value.");
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
    }

    @ExceptionHandler(NoRecordsFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoRecordFoundException(HttpServletRequest req, NoRecordsFoundException ex) {
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(HttpStatus.NO_CONTENT.value());
        error.setMessage("No records found.");
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
    }
}
