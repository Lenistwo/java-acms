package pl.xisp.core.controller.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.xisp.core.exception.DHCPLeaseNotFoundException;
import pl.xisp.core.exception.MessageNotFoundException;
import pl.xisp.core.exception.PPoEUserNotFoundException;
import pl.xisp.core.exception.TicketNotFoundException;
import pl.xisp.shared.exception.CustomException;
import pl.xisp.shared.response.Response;

@ControllerAdvice
public class CoreControllerAdvice {

    private static final boolean STATUS = true;

    @ExceptionHandler({ PPoEUserNotFoundException.class, DHCPLeaseNotFoundException.class,
                        TicketNotFoundException.class, MessageNotFoundException.class })
    public ResponseEntity<Response<String>> handleNotFoundException(CustomException exception) {
        return new ResponseEntity<>(new Response<>(STATUS, exception.getCode()), HttpStatus.NOT_FOUND);
    }
}
