package org.skroba.vk.statistic;

import java.util.List;

public interface PostsStatistic {
    List<Integer> getStatistic(String tag, long fromTime, int hours);
}
