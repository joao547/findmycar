package br.edu.ifpe.tads.findmycar.exceptions;

public class InvalidUserType extends RuntimeException{
    public InvalidUserType(String message) {
        super(message);
    }

    public InvalidUserType(String message, Throwable cause) {
        super(message, cause);
    }
}
