package com.db.exception;

/**
 * Created by mmaykarkar on 12/02/17.
 */
public class InvalidFieldException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String field;

    public InvalidFieldException(String field) {
        this.field = field;
    }

    public String getField()
    {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
