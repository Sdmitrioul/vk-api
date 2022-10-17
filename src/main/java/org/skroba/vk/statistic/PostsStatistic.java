package org.skroba.vk.statistic;

import java.util.List;
import java.util.Map;

public interface PostsStatistic {
    List<Integer> getStatistic(String tag, long startTime, int hours);
    Map<String, String> getParams(String tag, long startTime, int hours);
}
