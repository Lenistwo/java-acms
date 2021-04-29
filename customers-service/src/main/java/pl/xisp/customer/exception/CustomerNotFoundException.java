package pl.xisp.customer.exception;

import pl.xisp.shared.exception.CustomException;

public class CustomerNotFoundException extends CustomException {
    public CustomerNotFoundException() {
        super("CUSTOMER_NOT_FOUND");
    }
}
