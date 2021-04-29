package pl.xisp.customer.document.customer;

import lombok.Data;

@Data
public class Contact {
    private String name;
    private String description;

    private String phone;
    private String mail;

    private ContactTag tags;
}
