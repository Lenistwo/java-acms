package pl.xisp.auth.exception;

import pl.xisp.shared.exception.CustomException;

public class PasswordChangeConfirmedException extends CustomException {
    public PasswordChangeConfirmedException() {
        super("PASSWORD_CHANGE_MUST_BE_CONFIRMED");
    }
}
