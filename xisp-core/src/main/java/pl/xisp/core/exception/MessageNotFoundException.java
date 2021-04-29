package pl.xisp.core.exception;

import pl.xisp.shared.exception.CustomException;

public class MessageNotFoundException extends CustomException {
    public MessageNotFoundException() {
        super("MESSAGE_NOT_FOUND");
    }
}
