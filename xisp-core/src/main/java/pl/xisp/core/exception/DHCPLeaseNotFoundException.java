package pl.xisp.core.exception;

import pl.xisp.shared.exception.CustomException;

public class DHCPLeaseNotFoundException extends CustomException {
    public DHCPLeaseNotFoundException() {
        super("DHCP Lease Not Found");
    }
}
