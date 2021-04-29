package pl.xisp.core.repository.access;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.xisp.core.documents.access.dhcp.DHCPLease;

import java.util.Optional;

@Repository
public interface DHCPLeaseRepository extends MongoRepository<DHCPLease, String> {
    Optional<DHCPLease> findByMac(String mac);

    Optional<DHCPLease> findByAddress(String address);
}
