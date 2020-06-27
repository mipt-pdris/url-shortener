package ru.netology.testcase.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.function.BiConsumer;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice("ru.netology.testcase")
public class ExceptionHandlerz extends ResponseEntityExceptionHandler {
    @ExceptionHandler({IllegalArgumentException.class})
    public final ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        return doHandle(ex, BAD_REQUEST, logger::warn);
    }

    private ResponseEntity<String> doHandle(Exception ex, HttpStatus status, BiConsumer<String, Throwable> log) {
        log.accept(status.getReasonPhrase(), ex);
        return new ResponseEntity<>(ex.getLocalizedMessage(), status);
    }
}
