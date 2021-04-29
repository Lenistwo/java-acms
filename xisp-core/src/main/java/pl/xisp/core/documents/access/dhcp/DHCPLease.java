package pl.xisp.core.documents.access.dhcp;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;

@Data
@Document(collection = "dhcp_leases")
public class DHCPLease {
    @Id
    private String mac;
    private String address;

    private HashMap<String, String> attributes = new HashMap<>();
}
