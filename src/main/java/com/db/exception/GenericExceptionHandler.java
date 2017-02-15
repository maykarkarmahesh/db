package com.db.exception;

import com.db.utils.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

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
        error.setMessage(Constants.VALID_VALUE_ERROR_MESSAGE);
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
    }

    @ExceptionHandler(NoRecordsFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoRecordFoundException(HttpServletRequest req, NoRecordsFoundException ex) {
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(HttpStatus.NO_CONTENT.value());
        error.setMessage(Constants.NO_RECORD_ERROR_MESSAGE);
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
    }
}
