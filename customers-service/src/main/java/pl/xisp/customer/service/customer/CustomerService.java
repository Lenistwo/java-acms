package pl.xisp.customer.service.customer;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.xisp.customer.document.customer.Customer;
import pl.xisp.customer.exception.CustomerNotFoundException;
import pl.xisp.customer.repository.customer.CustomerRepository;
import pl.xisp.shared.response.PageableResponse;
import pl.xisp.shared.response.Pagination;
import pl.xisp.shared.response.Response;
import pl.xisp.shared.util.SearchUtil;

import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class CustomerService {
    private static final boolean STATUS = true;

    private final CustomerRepository customerRepository;
    private final SearchUtil<Customer> searchUtil;

    public Response<Customer> createCustomer(Customer customer) {
        customer.setId(UUID.randomUUID().toString());
        customer.setCreatedAt(LocalDateTime.now());
        customerRepository.save(customer);
        return new Response<>(STATUS, customer);
    }


    public Response<Customer> updateCustomer(String customerId, Customer customer) {
        if (!customerRepository.existsById(customerId)) throw new CustomerNotFoundException();
        customerRepository.save(customer);
        return new Response<>(STATUS, customer);
    }

    public Response<Map<String, String>> deleteCustomerById(String customerId) {
        if (!customerRepository.existsById(customerId)) throw new CustomerNotFoundException();
        customerRepository.deleteById(customerId);
        return new Response<>(STATUS, Collections.emptyMap());
    }

    public Response<Customer> getCustomerById(String customerId) {
        return new Response<>(STATUS, customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new));
    }

    public PageableResponse<List<Customer>> getAllCustomers(int page, int limit, String search) {
        return searchUtil.prepareSearchResponse(page, limit, search, Customer.class).orElse(getAllCustomer(page, limit));
    }

    private PageableResponse<List<Customer>> getAllCustomer(int page, int limit) {
        var pageRequest = PageRequest.of(page, limit);
        var pageableResponse = customerRepository.findAll(pageRequest);
        var pagination = new Pagination(page, pageableResponse.getSize(), pageableResponse.getTotalElements(), pageableResponse.getTotalPages());
        return new PageableResponse<>(STATUS, pageableResponse.getContent(), pagination);
    }
}
