package org.skroba.vk.url;

import java.util.Map;

public class VkUrlGenerator implements UrlGenerator {
    private static final String HOST = "api.vk.com";
    private static final String ACCESS_TOKEN_FIELD_NAME = "access_token";
    private static final String VERSION_FIELD_NAME = "v";
    
    private final String prefix;
    private final String accessToken;
    private final String version;
    
    public VkUrlGenerator(String prefix, String accessToken, String version) {
        this.prefix = prefix;
        this.accessToken = accessToken;
        this.version = version;
    }
    
    @Override
    public String getHost() {
        return HOST;
    }
    
    @Override
    public String getUrlString(Map<String, String> params) {
        StringBuilder url = new StringBuilder(
                UrlGenerator.constructDomain(true, HOST, "method")
        );
        url.append(SLASH).append(prefix).append(PARAMS);
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
