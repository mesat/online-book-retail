package org.openreading.readingisgood.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/** @author Muhammet Sakarya created at 8/15/2021 */
//@RestControllerAdvice
public class ExceptionHelper extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {OpenReadingControllerException.class})
  protected ResponseEntity<Object> handleControllerException(OpenReadingControllerException ex, WebRequest request) {
    return handleExceptionInternal(
        ex,
        ex.getBasicResponse(),
        new HttpHeaders(),
        HttpStatus.INTERNAL_SERVER_ERROR,
        request);
  }
  @ExceptionHandler(value = {RuntimeException.class})
  protected ResponseEntity<Object> handleCommonException(RuntimeException ex, WebRequest request) {
    return handleExceptionInternal(
            ex,
            "",
            new HttpHeaders(),
            HttpStatus.INTERNAL_SERVER_ERROR,
            request);
  }
}
