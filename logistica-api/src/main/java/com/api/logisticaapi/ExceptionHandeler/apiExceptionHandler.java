package com.api.logisticaapi.ExceptionHandeler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.api.logisticaapi.Domain.Exceptions.DomainException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class apiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<ErrorMessageResponse.Argument> invalidArguments = new ArrayList<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String errorName = ((FieldError) error).getField();
            String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            // String message = error.getDefaultMessage(); PEGA A MENSAGEM PADRAO
            invalidArguments.add(new ErrorMessageResponse.Argument(errorName, message));
        });

        ErrorMessageResponse errorMessage = new ErrorMessageResponse(status.value(), "Error", OffsetDateTime.now(),
                invalidArguments);
        return handleExceptionInternal(ex, errorMessage, headers, status, request);
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<Object> handleDomain(DomainException ex, WebRequest req) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ErrorMessageResponse errorMessage = new ErrorMessageResponse();
        errorMessage.setHttpStatus(status.value());
        errorMessage.setDateTime(OffsetDateTime.now());
        errorMessage.setMessage(ex.getMessage());

        return handleExceptionInternal(ex, errorMessage, new HttpHeaders(), status, req);
    }

}
