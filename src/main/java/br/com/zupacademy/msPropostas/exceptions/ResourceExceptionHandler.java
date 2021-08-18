package br.com.zupacademy.msPropostas.exceptions;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

public class ResourceExceptionHandler {

    private final MessageSource messageSource;

    public ResourceExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<FieldMessage> captureInvalidFields (MethodArgumentNotValidException e) {
        List<FieldMessage> errors = new ArrayList<>();

        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        fieldErrors.forEach(error -> {
            String msg = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            FieldMessage er = new FieldMessage(error.getField(), msg);
            errors.add(er);
        });
        return errors;
    }
}
