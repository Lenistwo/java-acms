package pl.xisp.auth.exception;

import pl.xisp.shared.exception.CustomException;

public class RemindPasswordNotFound extends CustomException {

    public RemindPasswordNotFound() {
        super("Password Remind Not Found");
    }
}
