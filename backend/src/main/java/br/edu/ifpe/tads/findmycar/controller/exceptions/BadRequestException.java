package br.edu.ifpe.tads.findmycar.controller.exceptions;

public class BadRequestException extends Exception {
    public BadRequestException(String msg){
        super(msg);
    }
}
