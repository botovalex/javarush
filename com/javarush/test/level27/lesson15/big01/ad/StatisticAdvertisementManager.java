package com.javarush.test.level27.lesson15.big01.ad;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by barbudos on 05.09.2016.
 */
public class StatisticAdvertisementManager {
    private static StatisticAdvertisementManager ourInstance = new StatisticAdvertisementManager();
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();

    public static StatisticAdvertisementManager getInstance() {
        return ourInstance;
    }

    private StatisticAdvertisementManager() {
    }

    public Map<String, Integer> getAdvertisementVideo(boolean isActive) {
        Map<String, Integer> result = new HashMap<>();
        ArrayList<Advertisement> advertisements = new ArrayList<>(storage.list());

        for (Advertisement ad : advertisements) {
            if (isActive && ad.getHits() > 0) {
                result.put(ad.getName(), result.containsKey(ad.getName()) ? result.get(ad.getName()) + ad.getHits() : ad.getHits());
            } else if (!isActive && ad.getHits() <= 0) {
                result.put(ad.getName(), result.containsKey(ad.getName()) ? result.get(ad.getName()) + ad.getHits() : ad.getHits());
            }
        }

        return result;
    }
}

/*
First Video - 100
second video - 10
Third Video - 2
четвертое видео - 4
 */