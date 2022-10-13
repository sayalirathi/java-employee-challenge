package com.example.rqchallenge.util;

public class RQChallengeException extends RuntimeException{


    private static final long serialVersionUID = 6288674385240639364L;

    public RQChallengeException (String errorMessage)  
        {  
            // calling the constructor of parent Exception  
            super(errorMessage);  
        }  
}
