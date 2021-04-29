package pl.xisp.customer.repository.finance;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.xisp.customer.document.finance.Tax;

@Repository
public interface TaxRepository extends MongoRepository<Tax, String> {
}
