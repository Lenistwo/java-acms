package pl.xisp.customer.document.finance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@Document(collection = "taxes")
public class Tax {
    @Id
    private String id;
    private String name;
    private int rate;
    private LocalDateTime createdAt;

    public Tax() {
        this.id = UUID.randomUUID().toString();
    }

    public Tax(String name, int rate) {
        this();
        this.name = name;
        this.rate = rate;
        this.createdAt = LocalDateTime.now();
    }
}
