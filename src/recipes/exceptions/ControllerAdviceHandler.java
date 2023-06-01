package recipes.exceptions;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.BindException;

@RestControllerAdvice
public class ControllerAdviceHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Void> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<Void> handleBindExceptions(BindException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    @ExceptionHandler(RecipeNotFoundException.class)
    public ResponseEntity<Void> handleRecipeNotFoundException(RecipeNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
