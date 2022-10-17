package org.skroba.vk.http;

import java.util.Map;

public interface Client {
    String sendRequest(Map<String, String> params);
    String getHost();
    boolean isHealthy();
}
