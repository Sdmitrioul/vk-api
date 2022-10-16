package org.skroba.vk.url;

import java.util.Map;

public interface UrlGenerator {
    char DELIMITER = '&';
    char EQUAL = '=';
    
    String getUrl(Map<String, String> params);
}
