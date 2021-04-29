package pl.xisp.core.service.message;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.xisp.core.documents.message.Message;
import pl.xisp.core.exception.MessageNotFoundException;
import pl.xisp.core.repository.message.MessageRepository;
import pl.xisp.shared.response.PageableResponse;
import pl.xisp.shared.response.Pagination;
import pl.xisp.shared.response.Response;
import pl.xisp.shared.util.SearchUtil;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class MessageService {
    private static final boolean STATUS = true;

    private final SearchUtil<Message> searchUtil;
    private final MessageRepository messageRepository;

    public Response<Message> getMessageById(String messageId) {
        return new Response<>(STATUS, messageRepository.findById(messageId).orElseThrow(MessageNotFoundException::new));
    }

    public PageableResponse<List<Message>> findAllMessages(int page, int limit, String search) {
        return Optional.ofNullable(searchUtil.prepareSearchResponse(page, limit, search, Message.class))
                .orElse(getAllMessages(page, limit));
    }

    private PageableResponse<List<Message>> getAllMessages(int page, int limit) {
        var sortByDateDesc = Sort.by(Sort.Direction.DESC, "addDate");
        var pageable = PageRequest.of(page, limit, sortByDateDesc);
        var pageableResponse = messageRepository.findAll(pageable);
        var pagination = new Pagination(page, pageableResponse.getSize(), pageableResponse.getTotalElements(),
                                        pageableResponse.getTotalPages());
        return new PageableResponse<>(STATUS, pageableResponse.getContent(), pagination);
    }

    public Response<Map<String, String>> createMessage(Message message) {
        message.setId(UUID.randomUUID().toString());
        message.setAddDate(LocalDateTime.now());

        messageRepository.save(message);
        return new Response<>(STATUS, Collections.emptyMap());
    }

    public Response<Map<String, String>> updateMessage(String messageId, Message message) {
        if (!messageRepository.existsById(messageId)) throw new MessageNotFoundException();

        message.setAddDate(LocalDateTime.now());

        messageRepository.save(message);

        return new Response<>(STATUS, Collections.emptyMap());
    }

    public Response<Map<String, String>> deleteMessageById(String messageId) {
        if (!messageRepository.existsById(messageId)) throw new MessageNotFoundException();

        messageRepository.deleteById(messageId);

        return new Response<>(STATUS, Collections.emptyMap());
    }
}
