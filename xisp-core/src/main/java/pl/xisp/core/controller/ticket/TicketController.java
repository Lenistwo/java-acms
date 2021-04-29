package pl.xisp.core.controller.ticket;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.xisp.core.documents.ticket.Ticket;
import pl.xisp.core.service.ticket.TicketService;
import pl.xisp.shared.response.PageableResponse;
import pl.xisp.shared.response.Response;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ticket")
public class TicketController {

    private final TicketService ticketService;

    @GetMapping
    public PageableResponse<List<Ticket>> getAllTicket(@RequestParam(required = false, defaultValue = "0") int page,
                                                       @RequestParam(required = false, defaultValue = "5") int limit,
                                                       @RequestParam(required = false, defaultValue = "") String search) {
        return ticketService.findAll(page, limit, search);
    }

    @GetMapping("/{ticketId}")
    public Response<Ticket> getTicketById(@PathVariable String ticketId) {
        return ticketService.getTicketById(ticketId);
    }

    @GetMapping("/from")
    public Response<List<Ticket>> getAllTicketsFrom(@RequestParam String from) {
        return ticketService.getAllFrom(from);
    }

    @PostMapping
    public Response<String> createTicket(@RequestBody Ticket ticket) {
        return ticketService.createTicket(ticket);
    }

    @PutMapping
    public Response<Map<String, String>> updateTicket(@RequestBody Ticket ticket) {
        return ticketService.updateTicket(ticket);
    }

    @DeleteMapping("/{ticketId}")
    public Response<Map<String, String>> deleteTicketById(@PathVariable String ticketId) {
        return ticketService.deleteTicket(ticketId);
    }

    @PostMapping("/communication/{ticketId}")
    public Response<String> appendCommunication(@PathVariable String ticketId, @RequestBody Ticket communicationDto) {
        return ticketService.appendCommunication(ticketId, communicationDto);
    }
}
