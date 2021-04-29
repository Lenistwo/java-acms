package pl.xisp.auth.exception;

import pl.xisp.shared.exception.CustomException;

public class AuthorizationException extends CustomException {
    public AuthorizationException() {
        super("2FA_INCORRECT");
    }
}
