package pl.xisp.core.exception;

import pl.xisp.shared.exception.CustomException;

public class NetworkDeviceNotFoundException extends CustomException {
    public NetworkDeviceNotFoundException() {
        super("NETWORK_DEVICE_NOT_FOUND_EXCEPTION");
    }
}
