package pl.xisp.customer.document.service.plan;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.xisp.customer.document.service.ServiceType;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "service-plans")
public class ServicePlan {
    @Id
    private String id;
    private String name;
    private int agreementLength;

    private int activationCost;
    private int installationCost;

    private int cost;
    private String tax;

    private SpeedLimit downloadSpeed;
    private SpeedLimit uploadSpeed;
    private int dataLimit;
    private ServiceType planType;
    private LocalDateTime createdAt;

    private List<Bonus> bonuses;
}
