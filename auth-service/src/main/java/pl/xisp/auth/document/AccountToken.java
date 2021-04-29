package pl.xisp.auth.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document(collection = "tokens")
public class AccountToken {
    @Id
    private String token;
    private String useragent;
    private String address;

    private String account;

    private long created;
    private long expires;

    public AccountToken() {
        this.token = UUID.randomUUID().toString();
    }
}
