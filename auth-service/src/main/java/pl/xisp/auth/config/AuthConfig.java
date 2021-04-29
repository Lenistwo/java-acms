package pl.xisp.auth.config;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthConfig {
    @Bean
    public GoogleAuthenticator authenticator() {
        return new GoogleAuthenticator();
    }

}
