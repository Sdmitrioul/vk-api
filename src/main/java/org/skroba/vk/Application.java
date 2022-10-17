package org.skroba.vk;

import org.skroba.vk.http.Client;
import org.skroba.vk.http.ClientImpl;
import org.skroba.vk.parser.Parser;
import org.skroba.vk.parser.VkResponseParser;
import org.skroba.vk.statistic.PostsStatistic;
import org.skroba.vk.statistic.VkPostsStatistic;
import org.skroba.vk.url.VkUrlGenerator;
import org.skroba.vk.util.VkResponse;

import java.util.Arrays;

public class Application {
    public static void main(String[] args) {
        if (args.length < 2 || Arrays.stream(args).anyMatch(s -> s == null || s.isEmpty()) || isRightInteger(args[1])) {
            System.out.println("First arg is tag, and second is number of hours between 0 and 24");
        }
        
        String token = System.getenv().getOrDefault("VK_ACCESS_TOKEN", "-");
        String version = System.getenv().getOrDefault("VERSION", "-");
        String prefix = "newsfeed.search";
        
        Client client = new ClientImpl(new VkUrlGenerator(prefix, token, version));
        Parser<VkResponse> parser = new VkResponseParser();
    
        PostsStatistic postsStatistic = new VkPostsStatistic(client, parser);
    
        System.out.println(postsStatistic.getStatistic(args[0], System.currentTimeMillis(), Integer.parseInt(args[1])));
    }
    
    public static boolean isRightInteger(String s) {
        try {
            int result = Integer.parseInt(s);
            return result > 0 && result <= 24;
        } catch(NumberFormatException | NullPointerException e) {
            return false;
        }
    }
}
