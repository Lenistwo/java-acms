package pl.xisp.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.xisp.auth.exception.*;
import pl.xisp.shared.exception.CustomException;
import pl.xisp.shared.exception.MailApiException;
import pl.xisp.shared.exception.SmsApiException;
import pl.xisp.shared.response.Response;

@ControllerAdvice
public class AuthControllerAdvice {

    private static final boolean STATUS = false;

    @ExceptionHandler(
            {AccountNotFoundException.class, AccountTokenNotFoundException.class, RemindPasswordNotFound.class})
    public ResponseEntity<Response<String>> handleNotFoundException(CustomException exception) {
        return new ResponseEntity<>(new Response<>(STATUS, exception.getCode()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(
            {BadCredentialsException.class, AccountInactiveException.class, PasswordChangeConfirmedException.class, AuthorizationException.class})
    public ResponseEntity<Response<String>> handleForbiddenException(CustomException exception) {
        return new ResponseEntity<>(new Response<>(STATUS, exception.getCode()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(
            {SmsApiException.class, MailApiException.class, InvalidPhoneNumberException.class, AccountExistsException.class})
    public ResponseEntity<Response<String>> handleConflictException(CustomException exception) {
        return new ResponseEntity<>(new Response<>(STATUS, exception.getCode()), HttpStatus.CONFLICT);
    }
}
