package com.gateaway.gateaway.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

  @Bean
  public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
    return http
      .csrf(ServerHttpSecurity.CsrfSpec::disable)
      .authorizeExchange(exchange -> exchange
        .pathMatchers(
          "/actuator/prometheus", // Prometheus scrape
          "/",
          "/users/**",
          "/roles/**",
          "/groups/**",
          "/permissions/**",
          "/organizations/**",

          "/auths/**"
        ).permitAll()
        .anyExchange().authenticated()
      )
      .build();
  }
}
