package br.com.fiap.pettech.pettech.exception;

public class ControllerNotFoundException  extends RuntimeException{

    public ControllerNotFoundException(String message){
        super(message);
    }
}
