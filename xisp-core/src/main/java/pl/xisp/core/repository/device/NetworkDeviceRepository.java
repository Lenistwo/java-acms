package pl.xisp.core.repository.device;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.xisp.core.documents.device.NetworkDevice;

@Repository
public interface NetworkDeviceRepository extends MongoRepository<NetworkDevice, String> {
}
