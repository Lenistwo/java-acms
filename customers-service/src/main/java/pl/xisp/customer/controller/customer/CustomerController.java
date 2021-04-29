package pl.xisp.customer.controller.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.xisp.customer.document.customer.Customer;
import pl.xisp.customer.service.customer.CustomerService;
import pl.xisp.shared.response.PageableResponse;
import pl.xisp.shared.response.Response;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public PageableResponse<List<Customer>> getAll(@RequestParam(required = false, defaultValue = "0") int page,
                                                   @RequestParam(required = false, defaultValue = "5") int limit,
                                                   @RequestParam(required = false, defaultValue = "") String search) {
        return customerService.getAllCustomers(page, limit, search);
    }

    @GetMapping("/{customerId}")
    public Response<Customer> getById(@PathVariable String customerId) {
        return customerService.getCustomerById(customerId);
    }

    @PostMapping
    public Response<Customer> addCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @PutMapping("/{customerId}")
    public Response<Customer> updateCustomer(@PathVariable String customerId, @RequestBody Customer customer) {
        return customerService.updateCustomer(customerId, customer);
    }

    @DeleteMapping("/{customerId}")
    public Response<Map<String, String>> deleteCustomer(@PathVariable String customerId) {
        return customerService.deleteCustomerById(customerId);
    }
}
