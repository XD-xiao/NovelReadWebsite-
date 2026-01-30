// com.example.springtemplate.Exception.TokenInvalidException.java
package com.example.springtemplate.Exception;

public class TokenInvalidException extends RuntimeException {
    public TokenInvalidException(String message) {
        super(message);
    }
}