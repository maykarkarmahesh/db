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
public class InvalidFieldExceptionHandler extends  RuntimeException {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidFieldException.class)
    public Map<String, String> handleProjectNotFoundException(HttpServletRequest req, InvalidFieldException ex) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", ex.getField());
        map.put("reason", "Project can not be found");
        map.put("url", req.getRequestURI());
        BodyBuilder responseBudiler = status(HttpStatus.BAD_REQUEST);
        ResponseEntity<Object> response = responseBudiler.body(map);
        return map;
    }
}
