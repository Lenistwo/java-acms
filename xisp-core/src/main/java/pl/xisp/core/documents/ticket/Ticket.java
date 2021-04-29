package pl.xisp.core.documents.ticket;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.xisp.shared.documents.TicketStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document
public class Ticket {
    private String id;
    private String from;
    private String title;
    private String content;
    private TicketStatus status;
    private LocalDateTime sentDate;
    private List<Communication> communications;
}
