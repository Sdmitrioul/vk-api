package org.skroba.vk.http;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skroba.vk.url.UrlGenerator;

import java.io.UncheckedIOException;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.status;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@WireMockTest(httpPort = ClientImplTests.PORT)
public class ClientImplTests {
    public static final int PORT = 8443;
    
    private Client client;
    
    @BeforeEach
    void init() {
        client = new ClientImpl(new UrlGenerator() {
            @Override
            public String getUrlString(Map<String, String> params) {
                return UrlGenerator.constructDomain(false, getHost() + ":" + PORT, "hello");
            }
            
            @Override
            public String getHost() {
                return "localhost";
            }
        });
    }
    
    @Test
    public void testIfServerIsHealthy() {
        stubFor(post("/ping").willReturn(ok()));
        stubFor(post("/ping6").willReturn(ok()));
        
        assertTrue(client.isHealthy());
    }
    
    @Test
    public void testWithData() {
        final String result = "Hello";
        stubFor(post("/ping").willReturn(ok()));
        stubFor(post("/ping6").willReturn(ok()));
        stubFor(get("/hello").withHost(equalTo("localhost")).willReturn(ok().withBody(result)));
        
        assertTrue(client.isHealthy());
        assertEquals(result + '\n', client.sendRequest(Map.of()));
    }
    
    @Test
    public void testWithException() {
        stubFor(get("/hello").withHost(equalTo("localhost")).willReturn(status(404)));
        
        assertThrows(UncheckedIOException.class, () -> client.sendRequest(Map.of()));
    }
}
