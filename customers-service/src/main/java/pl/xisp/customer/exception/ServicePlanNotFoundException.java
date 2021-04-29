package pl.xisp.customer.exception;

import pl.xisp.shared.exception.CustomException;

public class ServicePlanNotFoundException extends CustomException {
    public ServicePlanNotFoundException() {
        super("SERVICE_PLAN_NOT_FOUND_EXCEPTION");
    }
}
