package pl.xisp.shared.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class MainConfig {
    @Bean
    public Gson gson() {
        return new GsonBuilder().setPrettyPrinting().create();
    }

    @Value("${connection.timeout}")
    private long CONNECTION_TIMEOUT;

    @Value("${connection.timeout}")
    private long READ_TIMEOUT;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder().setConnectTimeout(Duration.ofMillis(CONNECTION_TIMEOUT)).setReadTimeout(Duration.ofMillis(READ_TIMEOUT)).build();
    }
}
