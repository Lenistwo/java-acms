package pl.xisp.customer.document.customer;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@SuppressWarnings("unused")
public class Address {
    private AddressType addressType;

    private String street;
    private String building;
    private String flat;
    private String zipCode;
    private String city;
    private String country;
}
