package pl.xisp.customer.repository.service;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.xisp.customer.document.service.plan.ServicePlan;

@Repository
public interface ServicePlanRepository extends MongoRepository<ServicePlan, String> {
}
