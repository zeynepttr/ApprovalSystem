package com.gateaway.gateaway.filter;



import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
public class LoggingFilter {

  @Bean
  public GlobalFilter logFilter() {
    return (exchange, chain) -> {
      log.info("Incoming request: {} {}",
        exchange.getRequest().getMethod(),
        exchange.getRequest().getURI());
      return chain.filter(exchange);
    };
  }
}
