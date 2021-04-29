package pl.xisp.auth.exception;

import pl.xisp.shared.exception.CustomException;

public class AccountTokenNotFoundException extends CustomException {
    public AccountTokenNotFoundException() {
        super("ACCOUNT_TOKEN_NOT_FOUND");
    }
}
