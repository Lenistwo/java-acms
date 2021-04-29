package pl.xisp.core.exception;

import pl.xisp.shared.exception.CustomException;

public class TicketNotFoundException  extends CustomException {
    public TicketNotFoundException() {
        super("TICKET_NOT_FOUND");
    }
}
