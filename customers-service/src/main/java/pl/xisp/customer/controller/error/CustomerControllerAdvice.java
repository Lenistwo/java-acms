package pl.xisp.customer.controller.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.xisp.customer.exception.*;
import pl.xisp.shared.exception.CustomException;
import pl.xisp.shared.response.Response;

import javax.security.auth.login.AccountNotFoundException;

@ControllerAdvice
public class CustomerControllerAdvice {
    private static final boolean STATUS = false;

    @ExceptionHandler({CustomerNotFoundException.class,
                              AccountNotFoundException.class,
                              CustomerNotFoundException.class,
                              ServiceNotFoundException.class,
                              BonusNotFoundException.class,
                              SurchargeNotFoundException.class,
                              TaxNotFoundException.class})
    public ResponseEntity<Response<String>> handleNotFoundException(CustomException exception) {
        return new ResponseEntity<>(new Response<>(STATUS, exception.getCode()), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler({AccountExistsException.class})
    public ResponseEntity<Response<String>> handleConflictException(CustomException exception) {
        return new ResponseEntity<>(new Response<>(STATUS, exception.getCode()), HttpStatus.CONFLICT);
    }
}
