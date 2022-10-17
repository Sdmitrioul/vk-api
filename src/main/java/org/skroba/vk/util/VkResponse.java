package org.skroba.vk.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VkResponse {
    private final List<Long> postTimes = new ArrayList<>();
    
    public List<Long> getPostTimes() {
        return postTimes;
    }
    
    public void addTime(long time) {
        postTimes.add(time);
    }
    
    @Override
    public String toString() {
        return "VkResponse{" +
                "postTimes=" + postTimes.stream().map(Object::toString).collect(Collectors.joining(", ")) +
                '}';
    }
}
