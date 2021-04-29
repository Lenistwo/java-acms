package pl.xisp.core.controller.message;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.xisp.core.documents.message.Message;
import pl.xisp.core.service.message.MessageService;
import pl.xisp.shared.response.PageableResponse;
import pl.xisp.shared.response.Response;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/message")
public class MessageController {

    private final MessageService messageService;

    @GetMapping
    public PageableResponse<List<Message>> getAllMessages(@RequestParam(required = false, defaultValue = "0") int page,
                                                          @RequestParam(required = false, defaultValue = "5") int limit,
                                                          @RequestParam(required = false, defaultValue = "") String search) {
        return messageService.findAllMessages(page, limit, search);
    }

    @GetMapping("{messageId}")
    public Response<Message> getByMessageId(@PathVariable String messageId) {
        return messageService.getMessageById(messageId);
    }

    @PostMapping
    public Response<Map<String, String>> addMessage(@RequestBody Message message) {
        return messageService.createMessage(message);
    }

    @PutMapping("/{messageId}")
    public Response<Map<String, String>> updateMessage(@RequestBody Message message, @PathVariable String messageId) {
        return messageService.updateMessage(messageId, message);
    }

    @DeleteMapping("/{messageId}")
    public Response<Map<String, String>> deleteMessageById(@PathVariable String messageId) {
        return messageService.deleteMessageById(messageId);
    }
}
