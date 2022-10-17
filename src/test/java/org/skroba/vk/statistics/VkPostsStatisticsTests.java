package org.skroba.vk.statistics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.skroba.vk.http.Client;
import org.skroba.vk.parser.Parser;
import org.skroba.vk.parser.VkResponseParser;
import org.skroba.vk.parser.VkResponseParserTests;
import org.skroba.vk.statistic.PostsStatistic;
import org.skroba.vk.statistic.VkPostsStatistic;
import org.skroba.vk.util.VkResponse;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

public class VkPostsStatisticsTests {
    private static final Parser<VkResponse> parser = new VkResponseParser();
    
    @Mock
    private Client client;
    
    private PostsStatistic statistic;
    
    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        statistic = new VkPostsStatistic(client, parser);
    }
    
    @Test
    void getGoodResult() {
        List<Integer> result = List.of(1, 1);
        when(client.isHealthy()).thenReturn(true);
        when(client.sendRequest(statistic.getParams("any", 43243244424000L, 2))).thenReturn(VkResponseParserTests.GOOD_RESPONSE);
        
        assertEquals(result, statistic.getStatistic("any", 43243244424000L, 2));
    }
    
    @Test
    void getGoodResultWithWrongParams() {
        List<Integer> result = List.of(1, 0);
        when(client.isHealthy()).thenReturn(true);
        when(client.sendRequest(statistic.getParams("any", 43243251424000L, 2))).thenReturn(VkResponseParserTests.GOOD_RESPONSE);
        
        assertEquals(result, statistic.getStatistic("any", 43243251424000L, 2));
    }
    
    @Test
    void getBadResult() {
        when(client.isHealthy()).thenReturn(true);
        when(client.sendRequest(statistic.getParams("any", 43243244424000L, 2))).thenReturn(VkResponseParserTests.BAD_RESPONSE);
        
        assertNull(statistic.getStatistic("any", 43243244424000L, 2));
    }
}
