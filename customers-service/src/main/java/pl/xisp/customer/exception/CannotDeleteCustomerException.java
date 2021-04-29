package pl.xisp.customer.exception;

import pl.xisp.shared.exception.CustomException;

public class CannotDeleteCustomerException extends CustomException {
    public CannotDeleteCustomerException() {
        super("CANNOT_DELETE_CUSTOMER");
    }
}
