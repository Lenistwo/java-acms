package pl.xisp.auth.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Document("remind-password")
public class RemindPassword {
    @Id
    private String id;
    private String accountId;
    private String code;

    private boolean smsConfirmed;
    private LocalDateTime createdAt;

    public RemindPassword() {
        this.id = UUID.randomUUID().toString();
        this.smsConfirmed = false;
    }

    public RemindPassword(String accountId, String code) {
        this();
        this.accountId = accountId;
        this.code = code;
        this.createdAt = LocalDateTime.now();
    }
}
