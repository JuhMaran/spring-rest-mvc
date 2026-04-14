package com.juhmaran.restmvc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * spring-rest-mvc
 *
 * @author Juliane Maran
 * @since 14/04/2026
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Value Not Found")
public class BreweryNotFoundException extends RuntimeException {

  public BreweryNotFoundException() {
  }

  public BreweryNotFoundException(String message) {
    super(message);
  }

  public BreweryNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public BreweryNotFoundException(Throwable cause) {
    super(cause);
  }

  public BreweryNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
