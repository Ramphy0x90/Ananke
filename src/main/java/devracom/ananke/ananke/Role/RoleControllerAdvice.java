package devracom.ananke.ananke.Role;

import devracom.ananke.ananke.Role.exceptions.RoleAlreadyExistsException;
import devracom.ananke.ananke.Role.exceptions.RoleNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RoleControllerAdvice {
    @ExceptionHandler(RoleAlreadyExistsException.class)
    public ResponseEntity<?> handleException(RoleAlreadyExistsException e) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(e.getMessage());
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<?> handleException(RoleNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }
}
