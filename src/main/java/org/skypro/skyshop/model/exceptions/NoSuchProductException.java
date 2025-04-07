package org.skypro.skyshop.model.exceptions;

public class NoSuchProductException extends RuntimeException{
    private final String code;
    public NoSuchProductException(String code, String message){
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
