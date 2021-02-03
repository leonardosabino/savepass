package br.com.savepass.savepass.exception;

public class ApiException extends RuntimeException {

    private static final long serialVersionUID = 6078781862424143535L;

    public String message;

    public ApiException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
