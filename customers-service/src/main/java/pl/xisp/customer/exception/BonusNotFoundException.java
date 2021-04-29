package pl.xisp.customer.exception;

import pl.xisp.shared.exception.CustomException;

public class BonusNotFoundException extends CustomException {
    public BonusNotFoundException() {
        super("BONUS_NOT_FOUND_EXCEPTION");
    }
}
