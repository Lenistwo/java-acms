package pl.xisp.shared.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.xisp.shared.exception.SmsApiException;
import pl.xisp.shared.model.SmsApiRequest;
import pl.xisp.shared.model.SmsApiResponse;

@Service
@RequiredArgsConstructor
public class SmsService {
    private static final String BASE_PATH = "https://api.smsapi.pl/mfa/codes";
    private static final String FROM = "Dreamlink";
    private static final String FAST = "0";

    private final RestTemplate template;

    @Value("${sms.api.key}")
    private String API_KEY;

    public SmsApiResponse send(String to) {
        var httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(API_KEY);
        var smsApiResponse = template.postForEntity(BASE_PATH, new HttpEntity<>(new SmsApiRequest(to, FROM, "Twój kod: [%code%]", FAST), httpHeaders), SmsApiResponse.class);
        if (!smsApiResponse.getStatusCode().is2xxSuccessful()) {
            throw new SmsApiException();
        }
        return smsApiResponse.getBody();
    }
}
