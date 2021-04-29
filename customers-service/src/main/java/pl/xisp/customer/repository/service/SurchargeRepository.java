package pl.xisp.customer.repository.service;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.xisp.customer.document.finance.Surcharge;

@Repository
public interface SurchargeRepository extends MongoRepository<Surcharge, String> {
}
