package org.skroba.vk.statistic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.skroba.vk.util.VkResponse;
import org.skroba.vk.http.Client;
import org.skroba.vk.parser.ParseException;
import org.skroba.vk.parser.Parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class VkPostsStatistic implements PostsStatistic {
    private static final Logger LOGGER = LogManager.getFormatterLogger(VkPostsStatistic.class);
    
    private static final int SECONDS_IN_HOUR = 3600;
    private static final int MILLISECONDS_IN_SECOND = 1000;
    
    private final Client client;
    private final Parser<VkResponse> parser;
    
    public VkPostsStatistic(Client client, Parser<VkResponse> parser) {
        this.client = client;
        this.parser = parser;
    }
    
    @Override
    public List<Integer> getStatistic(String tag, long startTime, int hours) {
        final long endTime = startTime / MILLISECONDS_IN_SECOND;
        final long fromTime = endTime - (long) SECONDS_IN_HOUR * hours;
        
        if (!client.isHealthy()) {
            LOGGER.warn("Server - " + client.getHost() + " is unavailable");
            
            return null;
        }
        
        Map<String, String> params = Map.of(
                "q", tag,
                "start_time", Long.toString(fromTime),
                "end_time", Long.toString(endTime));
        
        final String response = client.sendRequest(params);
        
        try {
            VkResponse data = parser.parse(response);
            
            return processData(data, fromTime, hours);
        } catch (ParseException e) {
            LOGGER.error("Can't parse response: " + e.getMessage());
            return null;
        }
    }
    
    private List<Integer> processData(VkResponse data, long beginTime, int hours) {
        List<Long> timesOfPosts = data.getPostTimes();
        List<Integer> countOfPosts = new ArrayList<>(Collections.nCopies(hours, 0));
        
        for (long time: timesOfPosts) {
            int hour = (int) ((time - beginTime) / SECONDS_IN_HOUR);
            
            if (hour > hours) {
                continue;
            }
            
            countOfPosts.set(hour, countOfPosts.get(hour) + 1);
        }
        
        return countOfPosts;
    }
}
