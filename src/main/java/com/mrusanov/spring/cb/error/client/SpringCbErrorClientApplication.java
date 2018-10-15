package com.mrusanov.spring.cb.error.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableCircuitBreaker
@RefreshScope
public class SpringCbErrorClientApplication {

    @Value("${client.server.readTimeoutInMilliseconds}")
    private Integer readTimeoutInMilliseconds;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .setReadTimeout(readTimeoutInMilliseconds)
                .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringCbErrorClientApplication.class, args);
    }

}
