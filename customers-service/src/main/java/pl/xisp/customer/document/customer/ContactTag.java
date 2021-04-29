package pl.xisp.customer.document.customer;

import lombok.Getter;

public enum ContactTag {
    BILLING(1), CONTACT(2);

    @Getter private final int type;

    ContactTag(int type) {
        this.type = type;
    }
}
