package pl.xisp.customer.document.customer;

import lombok.Getter;

public enum CustomerType {
    INDIVIDUAL(1), COMPANY(2);

    @Getter
    private final int type;

    CustomerType(int type) {
        this.type = type;
    }
}
