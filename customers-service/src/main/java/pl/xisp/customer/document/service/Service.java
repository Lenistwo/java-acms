package pl.xisp.customer.document.service;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.xisp.customer.document.service.plan.ServicePlan;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Document(collection = "services")
public class Service {
    @Id
    private String id;
    private String name;
    private ServiceType type;
    private ServicePlan plan;
    private List<String> surcharges;

    private LocalDateTime createdAt;
    private LocalDateTime activationTime;

    public Service() {
        this.id = UUID.randomUUID().toString();
    }
}
