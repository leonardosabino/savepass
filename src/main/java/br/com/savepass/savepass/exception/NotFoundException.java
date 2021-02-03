package br.com.savepass.savepass.exception;

import org.springframework.http.HttpStatus;

@ErrorStatus(HttpStatus.NO_CONTENT)
public class NotFoundException extends ApiException {

    private static final long serialVersionUID = -7436807216310502488L;

    public NotFoundException(String message) {
        super(message);
    }
}
