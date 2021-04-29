package pl.xisp.core.documents.message;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "message")
public class Message {
    @Id
    private String id;
    private String title;
    private String content;
    private String thumbnail;
    private String badge;
    private LocalDateTime addDate;
}
