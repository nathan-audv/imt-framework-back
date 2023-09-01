package imt.framework.back.imtframeworkback.core.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(value = {UserAlreadyExistException.class})
    public ResponseEntity<String> alreadyExist(Exception exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }
}
