package pl.xisp.customer.exception;

import pl.xisp.shared.exception.CustomException;

public class SurchargeNotFoundException extends CustomException {
    public SurchargeNotFoundException() {
        super("SURCHARGE_NOT_FOUND_EXCEPTION");
    }
}
