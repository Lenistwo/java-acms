package pl.xisp.customer.document.customer;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerAddresses {
    private Address main;
    private Address invoice;
}
