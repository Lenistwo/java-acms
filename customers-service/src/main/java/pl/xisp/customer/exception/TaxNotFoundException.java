package pl.xisp.customer.exception;

import pl.xisp.shared.exception.CustomException;

public class TaxNotFoundException extends CustomException {
    public TaxNotFoundException() {
        super("TAX_NOT_FOUND");
    }
}
