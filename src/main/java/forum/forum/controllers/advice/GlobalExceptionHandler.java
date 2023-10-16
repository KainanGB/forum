package forum.forum.controllers.advice;

import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(NoSuchElementException.class)
  public ResponseEntity<Object> handleItemNotFoundException(
          NoSuchElementException request
  ){
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(request.getMessage());
  }

  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<Object> handleValidationException(
          ValidationException request
  ){
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(request.getMessage());
  }

}
