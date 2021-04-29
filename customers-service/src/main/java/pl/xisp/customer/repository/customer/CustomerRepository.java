package pl.xisp.customer.repository.customer;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.xisp.customer.document.customer.Customer;
import pl.xisp.customer.document.customer.CustomerType;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
    Optional<Customer> findByPesel(String pesel);

    Optional<Customer> findByNip(String nip);

    Optional<Customer> findByRegon(String regon);

    Optional<Customer> findByKrs(String krs);

    List<Customer> findAllByType(CustomerType type);

    List<Customer> findAllByIdIn(List<String> ids);
}
