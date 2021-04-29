package pl.xisp.customer.document.customer;

import lombok.Getter;

public enum AddressType {
    BILLING(1), CONTACT(2), RESIDENTIAL(3);

    @Getter
    private final int type;

    AddressType(int type) {
        this.type = type;
    }
}
