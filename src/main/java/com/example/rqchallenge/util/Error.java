package com.example.rqchallenge.util;

import org.springframework.http.HttpStatus;

public class Error {
  private HttpStatus httpStatus;
  private String message;
  
  public Error() {
    super();
    
  }

  public Error(HttpStatus httpStatus, String message) {
    super();
    this.httpStatus = httpStatus;
    this.message = message;
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }

  public void setHttpStatus(HttpStatus httpStatus) {
    this.httpStatus = httpStatus;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
