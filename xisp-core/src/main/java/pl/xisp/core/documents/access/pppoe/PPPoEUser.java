package pl.xisp.core.documents.access.pppoe;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;

@Data
@Document(collection = "pppoe_users")
public class PPPoEUser {
    @Id
    private String name;
    private String password;
    private String address;

    private HashMap<String, String> attributes = new HashMap<>();
}
