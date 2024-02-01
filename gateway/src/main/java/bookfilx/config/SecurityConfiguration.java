package bookfilx.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(
        ServerHttpSecurity http
    ) {
        http
            .cors()
            .and()
            .csrf()
            .disable()
            .authorizeExchange()
            .pathMatchers("/login/**", "/logout**")
            .permitAll()
            .anyExchange()
            .authenticated()
            .and()
            .oauth2Login(); // to redirect to oauth2 login page.

        return http.build();
    }
}
