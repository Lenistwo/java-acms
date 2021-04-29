package pl.xisp.customer.document.finance;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Document(collection = "surcharges")
public class Surcharge {
    @Id
    private String id;
    private String name;
    private String description;

    private String tax;
    private int cost;
    private LocalDateTime createdAt;
}
