package com.bengonohugues.emprunt_service.Exception;

public class LivreNonDisponibleException extends RuntimeException{
    public LivreNonDisponibleException (String message){
        super(message);
    }
}
