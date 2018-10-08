package com.mrusanov.spring.cb.error.client.service;

import java.net.URI;

public interface ReadService {

    String read(URI uri);

    String readReliably(URI uri);

}
