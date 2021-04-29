package pl.xisp.core.repository.message;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.xisp.core.documents.message.Message;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {
}
