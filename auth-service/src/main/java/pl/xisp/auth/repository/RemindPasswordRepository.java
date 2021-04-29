package pl.xisp.auth.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.xisp.auth.document.RemindPassword;

import java.util.Optional;

@Repository
public interface RemindPasswordRepository extends MongoRepository<RemindPassword, String> {
    Optional<RemindPassword> findByCode(String code);
}
