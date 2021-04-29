package pl.xisp.core.documents.device;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.xisp.shared.model.Credentials;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "network-device")
public class NetworkDevice {
    @Id
    private String id;
    private String ip;
    private String sshPort;
    private Credentials credentials;
}
