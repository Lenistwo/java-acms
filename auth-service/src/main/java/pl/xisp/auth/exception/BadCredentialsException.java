package pl.xisp.auth.exception;

import pl.xisp.shared.exception.CustomException;

public class BadCredentialsException extends CustomException {
    public BadCredentialsException() {
        super("BAD_CREDENTIALS");
    }
}
