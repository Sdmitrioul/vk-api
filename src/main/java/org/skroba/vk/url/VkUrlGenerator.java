package org.skroba.vk.url;

import java.util.Map;

public class VkUrlGenerator implements UrlGenerator {
    private static final String DOMAIN = "https://api.vk.com/method/";
    private static final String ACCESS_TOKEN_FIELD_NAME = "access_token";
    private static final String VERSION_FIELD_NAME = "v";
    
    private final String prefix;
    private final String accessToken;
    private final String version;
    
    VkUrlGenerator(String prefix, String accessToken, String version) {
        this.prefix = prefix;
        this.accessToken = accessToken;
        this.version = version;
    }
    
    @Override
    public String getUrl(Map<String, String> params) {
        StringBuilder url = new StringBuilder(prefix);
        params.forEach((key, value) -> url
                .append(key)
                .append(EQUAL)
                .append(value)
                .append(DELIMITER));
        url.append(ACCESS_TOKEN_FIELD_NAME)
                .append(EQUAL)
                .append(accessToken
                ).append(DELIMITER)
                .append(VERSION_FIELD_NAME)
                .append(EQUAL)
                .append(version);
        return url.toString();
    }
}
