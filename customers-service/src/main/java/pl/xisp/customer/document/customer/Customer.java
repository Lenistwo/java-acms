package pl.xisp.customer.document.customer;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.xisp.customer.document.service.Agreement;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "customers")
public class Customer {
    @Id
    private String id;
    private CustomerType type;

    // Individual
    private String firstName;
    private String lastName;

    private String pesel;
    private String identity;
    private String passport;

    // Company
    private String name;

    private String nip;
    private String krs;
    private String regon;

    private int balance;
    private LocalDateTime createdAt;

    private CustomerAddresses addresses;
    private List<Contact> contacts;
    private List<String> services;
    private List<Agreement> agreements;
}
