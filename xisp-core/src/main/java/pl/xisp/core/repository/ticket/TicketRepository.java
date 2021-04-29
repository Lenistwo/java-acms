package pl.xisp.core.repository.ticket;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.xisp.core.documents.ticket.Ticket;
import pl.xisp.shared.documents.TicketStatus;

import java.util.List;

@Repository
public interface TicketRepository extends MongoRepository<Ticket, String> {

    List<Ticket> findAllByFrom(String from);

    Page<Ticket> findAllByStatus(TicketStatus status, Pageable pageable);
}
