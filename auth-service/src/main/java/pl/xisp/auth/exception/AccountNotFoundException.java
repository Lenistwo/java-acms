package pl.xisp.auth.exception;

import pl.xisp.shared.exception.CustomException;

public class AccountNotFoundException extends CustomException {
    public AccountNotFoundException() {
        super("ACCOUNT_NOT_FOUND");
    }
}
