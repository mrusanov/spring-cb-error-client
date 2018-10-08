package com.mrusanov.spring.cb.error.client.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class ReadServiceImpl implements ReadService {

    private RestTemplate restTemplate;

    @Autowired
    public ReadServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String read(URI uri) {
        return restTemplate.getForObject(uri, String.class);
    }

    @HystrixCommand(fallbackMethod = "fallback")
    public String readReliably(URI uri) {
        return restTemplate.getForObject(uri, String.class);
    }

    private String fallback(URI uri, Throwable cause) {
        System.out.println(cause.getMessage());
        return "Fallback called when reading from " + uri;
    }

}
