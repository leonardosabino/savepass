package br.com.savepass.savepass.exception;

import br.com.savepass.savepass.model.vo.ApiErrorVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalApiExceptionHandlerAdvice {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiErrorVO> handleApplicationException(final ApiException e) {
        HttpStatus httpStatus = getHttpStatus(e);

        ApiErrorVO body = new ApiErrorVO(httpStatus.value(), e.getMessage());
        return new ResponseEntity<>(body, httpStatus);
    }

    private HttpStatus getHttpStatus(ApiException e) {
        return (e.getClass().isAnnotationPresent(ErrorStatus.class))
                ? e.getClass().getAnnotation(ErrorStatus.class).value()
                : HttpStatus.BAD_REQUEST;
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiErrorVO handle(MethodArgumentNotValidException ex) {
        return new ApiErrorVO(HttpStatus.BAD_REQUEST.value(), getMethodArgumentErrors(ex.getBindingResult()));
    }

    private String getMethodArgumentErrors(BindingResult bindResults) {
        var res = new StringBuilder();
        for (ObjectError e : bindResults.getAllErrors()) {
            String field = ((FieldError) e).getField();
            res.append(field).append(": ").append(e.getDefaultMessage()).append("; ");
        }
        return res.toString();
    }
}