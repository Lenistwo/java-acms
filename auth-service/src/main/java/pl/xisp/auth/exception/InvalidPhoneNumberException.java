package pl.xisp.auth.exception;

import pl.xisp.shared.exception.CustomException;

public class InvalidPhoneNumberException extends CustomException {
    public InvalidPhoneNumberException() {
        super("INVALID_PHONE_NUMBER");
    }
}
