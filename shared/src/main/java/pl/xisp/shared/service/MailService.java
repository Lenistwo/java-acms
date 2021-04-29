package pl.xisp.shared.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pl.xisp.shared.exception.MailApiException;
import pl.xisp.shared.model.Message;

import javax.mail.MessagingException;

@Service
@RequiredArgsConstructor
public class MailService {
    @Value("${mail.from.address}")
    private String FROM;

    private final JavaMailSender mailSender;

    @Async
    public void sendMailAsync(Message message) {
        var mimeMessage = mailSender.createMimeMessage();
        var helper = new MimeMessageHelper(mimeMessage);
        try {
            helper.setFrom(FROM);
            helper.setTo(message.getTo());
            helper.setSubject(message.getSubject());
            helper.setText(message.getUrl(), true);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new MailApiException();
        }
    }
}
