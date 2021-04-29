package pl.xisp.core.exception;

import pl.xisp.shared.exception.CustomException;

public class PPoEUserNotFoundException extends CustomException {
    public PPoEUserNotFoundException() {
        super("PPoE User Not Found");
    }
}
