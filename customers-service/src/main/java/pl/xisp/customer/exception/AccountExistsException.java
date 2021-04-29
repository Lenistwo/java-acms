package pl.xisp.customer.exception;

import pl.xisp.shared.exception.CustomException;

public class AccountExistsException extends CustomException {
    public AccountExistsException() {
        super("ACCOUNT_EXIST_EXCEPTION");
    }
}
