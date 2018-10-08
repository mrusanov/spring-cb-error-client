package com.mrusanov.spring.cb.error.client.api;

import com.mrusanov.spring.cb.error.client.service.ReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class ClientApi {

    @Value("${server.url}")
    private String serverUrl;

    @Value("${server.errorEndpoint}")
    private String serverErrorEndpoint;

    private ReadService readService;

    @Autowired
    public ClientApi(ReadService readService) {
        this.readService = readService;
    }

    @RequestMapping(value = "/invoke-server/error/{errorCode}", params = "reliable", method = RequestMethod.GET)
    public String invokeServerWithError(@PathVariable(value = "errorCode") String errorCode, @RequestParam(value = "reliable") boolean reliable) {
        URI uri = URI.create(serverUrl + serverErrorEndpoint + errorCode);
        if(reliable) {
            return readService.readReliably(uri);
        }
        return readService.read(uri);
    }

}
