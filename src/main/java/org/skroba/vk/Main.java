package org.skroba.vk;

import org.skroba.vk.http.Client;
import org.skroba.vk.http.VkClient;
import org.skroba.vk.parser.Parser;
import org.skroba.vk.parser.VkResponseParser;
import org.skroba.vk.statistic.PostsStatistic;
import org.skroba.vk.statistic.VkPostsStatistic;
import org.skroba.vk.url.VkUrlGenerator;
import org.skroba.vk.util.VkResponse;

public class Main {
    public static void main(String[] args) {
        String token = System.getenv().getOrDefault("VK_ACCESS_TOKEN", "-");
        String version = System.getenv().getOrDefault("VERSION", "-");
        String prefix = "newsfeed.search";
        
        Client client = new VkClient(new VkUrlGenerator(prefix, token, version));
        Parser<VkResponse> parser = new VkResponseParser();
    
        PostsStatistic postsStatistic = new VkPostsStatistic(client, parser);
    
        System.err.println(postsStatistic.getStatistic(args[0], System.currentTimeMillis(), Integer.parseInt(args[1])));
    }
}
