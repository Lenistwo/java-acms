package pl.xisp.auth.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document("account")
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    private String id;
    private String mail;
    private String name;
    private String password;
    private String secret2F;
    private String phoneNumber;
    private AccountType accountType;
    private LocalDateTime createdAt;
    private boolean active;
}
