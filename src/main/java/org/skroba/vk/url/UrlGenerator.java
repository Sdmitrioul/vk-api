package org.skroba.vk.url;

import java.util.Arrays;
import java.util.Map;

public interface UrlGenerator {
    char DELIMITER = '&';
    char EQUAL = '=';
    char PARAMS = '?';
    char SLASH = '/';
    
    String HTTPS = "https";
    
    String HTTP = "http";
    
    static String constructDomain(boolean isHttps, String host, String... others) {
        StringBuilder result = new StringBuilder(isHttps ? HTTPS : HTTP);
        result.append("://").append(host).append(SLASH);
        
        Arrays.stream(others).forEach(
                part -> result.append(part).append(SLASH)
        );
    
        return result.toString();
    }
    
    String getUrlString(Map<String, String> params);
    
    String getHost();
}
