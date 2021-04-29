package pl.xisp.customer.exception;

import pl.xisp.shared.exception.CustomException;

public class ServiceNotFoundException extends CustomException {
    public ServiceNotFoundException() {
        super("SERVICE_NOT_FOUND");
    }
}
