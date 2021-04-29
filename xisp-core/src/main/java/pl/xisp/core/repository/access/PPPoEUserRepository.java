package pl.xisp.core.repository.access;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.xisp.core.documents.access.pppoe.PPPoEUser;

import java.util.Optional;

@Repository
public interface PPPoEUserRepository extends MongoRepository<PPPoEUser, String> {
    Optional<PPPoEUser> findByName(String mac);

    Optional<PPPoEUser> findByAddress(String address);
}
