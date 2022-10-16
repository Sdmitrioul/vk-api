package org.skroba.vk.url;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class VkUrlGeneratorTests {
    private static final String TOKEN = "FDKJNFJ#(*)@NJKNFD#@324";
    private static final String VERSION = "0.1.1";
    private static final String PREFIX = "api";
    
    UrlGenerator generator;
    
    @BeforeEach
    void createGenerator() {
        generator = new VkUrlGenerator(PREFIX, TOKEN, VERSION);
    }
    
    @Test
    void testContainingPrefix() {
        final String result = generator.getUrl(Map.of());
        assertTrue(result.contains(PREFIX));
    }
    
    @Test
    void testContainingToken() {
        final String result = generator.getUrl(Map.of());
        assertTrue(result.contains("access_token=" + TOKEN));
    }
    
    @Test
    void testContainingVersion() {
        final String result = generator.getUrl(Map.of());
        assertTrue(result.contains("v=" + VERSION));
    }
    
    @Test
    void testAllParams() {
        final Map<String, String> params = Map.of("first", "first", "second", "second");
        final String result = generator.getUrl(params);
        
        params.forEach((key, value) -> {
            assertTrue(result.contains(key + "=" + value));
        });
    }
}
