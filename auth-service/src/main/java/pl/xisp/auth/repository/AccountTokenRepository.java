package pl.xisp.auth.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.xisp.auth.document.AccountToken;

import java.util.List;

@Repository
public interface AccountTokenRepository extends MongoRepository<AccountToken, String> {
    List<AccountToken> findAllByAccountOrderByExpiresDesc(String account);
    List<AccountToken> findAllByAccount(String account);
}
