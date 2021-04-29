package pl.xisp.auth.task;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.xisp.auth.repository.AccountTokenRepository;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeleteExpiredTokens {

    private final AccountTokenRepository repository;

    @Scheduled(cron = "${task.delete.expired.tokens.cron}")
    public void deleteExpired() {
       var expiredTokens = repository.findAll().stream().filter(token -> token.getExpires() < System.currentTimeMillis()).collect(Collectors.toList());
        if (expiredTokens.isEmpty()) {
            return;
        }
        repository.deleteAll(expiredTokens);
    }
}
