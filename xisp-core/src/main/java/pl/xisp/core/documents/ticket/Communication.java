package pl.xisp.core.documents.ticket;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class Communication {
    @NonNull
    private String content;
    @NonNull
    private String links;
    private LocalDateTime timeStamp = LocalDateTime.now();
}
