package com.lcwd.electronic.store.ElectronicStore.exception;

public class BadApiRequest extends RuntimeException{
    public BadApiRequest(){
        super("Bad API Request");
    }
    public BadApiRequest(String message){
        super(message);
    }
}
