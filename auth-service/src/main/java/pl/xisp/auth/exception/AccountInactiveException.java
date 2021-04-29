package pl.xisp.auth.exception;

import pl.xisp.shared.exception.CustomException;

public class AccountInactiveException  extends CustomException {
    public AccountInactiveException() {
        super("ACCOUNT_INACTIVE");
    }
}
