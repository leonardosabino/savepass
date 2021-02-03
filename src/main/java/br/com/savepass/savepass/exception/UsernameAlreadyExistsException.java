package br.com.savepass.savepass.exception;

import org.springframework.http.HttpStatus;

@ErrorStatus(HttpStatus.CONFLICT)
public class UsernameAlreadyExistsException extends ApiException {

    private static final long serialVersionUID = 837455716168796221L;

    public UsernameAlreadyExistsException(String message){
        super(message);
    }
}
