package pl.xisp.customer.repository.service;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.xisp.customer.document.service.Service;

@Repository
public interface ServiceRepository extends MongoRepository<Service, String> {
}
