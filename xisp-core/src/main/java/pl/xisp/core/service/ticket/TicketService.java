package pl.xisp.core.service.ticket;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.xisp.core.documents.ticket.Ticket;
import pl.xisp.core.exception.TicketNotFoundException;
import pl.xisp.core.repository.ticket.TicketRepository;
import pl.xisp.shared.documents.TicketStatus;
import pl.xisp.shared.response.PageableResponse;
import pl.xisp.shared.response.Pagination;
import pl.xisp.shared.response.Response;
import pl.xisp.shared.util.SearchUtil;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TicketService {
    private static final boolean STATUS = true;

    private final TicketRepository ticketRepository;
    private final SearchUtil<Ticket> searchUtil;

    public Response<Ticket> getTicketById(String id) {
        return new Response<>(STATUS, ticketRepository.findById(id).orElseThrow(TicketNotFoundException::new));
    }

    public PageableResponse<List<Ticket>> findAll(int page, int limit, String search) {
        return Optional.ofNullable(searchUtil.prepareSearchResponse(page, limit, search, Ticket.class)).orElse(getAllTickets(page, limit));
    }

    public Response<String> createTicket(Ticket ticket) {
        ticket.setId(UUID.randomUUID().toString());
        ticket.setSentDate(LocalDateTime.now());
        ticketRepository.save(ticket);
        return new Response<>(STATUS, ticket.getId());
    }

    public Response<Map<String, String>> updateTicket(Ticket ticket) {
        if (ticketRepository.existsById(ticket.getId())) throw new TicketNotFoundException();
        ticketRepository.insert(ticket);
        return new Response<>(STATUS, Collections.emptyMap());
    }

    public Response<Map<String, String>> deleteTicket(String ticketId) {
        if (!ticketRepository.existsById(ticketId)) throw new TicketNotFoundException();
        ticketRepository.deleteById(ticketId);
        return new Response<>(STATUS, Collections.emptyMap());
    }

    private PageableResponse<List<Ticket>> getAllTickets(int page, int limit) {
        var pageRequest = PageRequest.of(page, limit);
        var pageableResponse = ticketRepository.findAllByStatus(TicketStatus.OPEN, pageRequest);
        var pagination = new Pagination(page, pageableResponse.getSize(), pageableResponse.getTotalElements(), pageableResponse.getTotalPages());
        return new PageableResponse<>(STATUS, pageableResponse.getContent(), pagination);
    }

    public Response<List<Ticket>> getAllFrom(String from) {
        return new Response<>(STATUS, ticketRepository.findAllByFrom(from));
    }

    public Response<String> appendCommunication(String ticketId, Ticket ticket) {
        if (!ticketRepository.existsById(ticketId)) throw new TicketNotFoundException();

        ticketRepository.insert(ticket);

        return new Response<>(STATUS, ticketId);
    }
}
