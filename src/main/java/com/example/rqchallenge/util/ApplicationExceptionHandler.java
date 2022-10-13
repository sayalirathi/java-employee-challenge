package com.example.rqchallenge.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@ResponseBody
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(RQChallengeException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<Error> handleException(RQChallengeException e) {
    Error error = new Error(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
    return new ResponseEntity<>(error, error.getHttpStatus());
}

}
