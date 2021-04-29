package pl.xisp.customer.document.service;

import lombok.Data;

import java.util.List;

@Data
public class Agreement {
    private int length;
    private int period;
    private AgreementStatus status;
    private List<Service> services;
}
