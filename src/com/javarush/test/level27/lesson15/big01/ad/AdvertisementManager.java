package com.javarush.test.level27.lesson15.big01.ad;

import com.javarush.test.level27.lesson15.big01.statistic.StatisticEventManager;
import com.javarush.test.level27.lesson15.big01.statistic.event.VideoSelectedEventDataRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AdvertisementManager {
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    private List<Advertisement> selectVideosToDisplay(int index, List<Advertisement> videos, int timeSeconds) {
        if (videos == null || videos.isEmpty() || index > videos.size() - 1) return new ArrayList<Advertisement>();
        Collections.sort(videos, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                long o1OneSecondCost = o1.getAmountPerOneDisplaying() * 1000 / o1.getDuration();
                long o2OneSecondCost = o2.getAmountPerOneDisplaying() * 1000 / o2.getDuration();
                if (o1OneSecondCost != o2OneSecondCost)
                    return Long.compare(o2OneSecondCost, o1OneSecondCost);
                else
                    return Integer.compare(o1.getDuration(), o2.getDuration());
            }
        });

        List<Advertisement> result = new ArrayList<Advertisement>();
        int localDuration = timeSeconds;
        for (int i = index; i < videos.size(); i++) {
            int videoDuration = videos.get(i).getDuration();
            if (videoDuration <= localDuration) {
                result.add(videos.get(i));
                localDuration -= videoDuration;
                if (localDuration <= 0) break;
            }
        }
        List<Advertisement> tempResult = selectVideosToDisplay(index + 1, videos, timeSeconds);
        if (tempResult.isEmpty()) return result;

        long amountResult = 0;
        int sumTimeResult = 0;
        for (Advertisement ad : result) {
            amountResult += ad.getAmountPerOneDisplaying();
            sumTimeResult += ad.getDuration();
        }

        long amountTemp = 0;
        int sumTimeTemp = 0;
        for (Advertisement ad : tempResult) {
            amountTemp += ad.getAmountPerOneDisplaying();
            sumTimeTemp += ad.getDuration();
        }

        if (amountResult != amountTemp)
            return amountResult > amountTemp ? result : tempResult;
        else if (sumTimeResult != sumTimeTemp)
            return sumTimeResult > sumTimeTemp ? result : tempResult;
        else return result.size() <= tempResult.size() ? result : tempResult;
    }

    public void processVideos() throws NoVideoAvailableException {
        List<Advertisement> copy = new ArrayList<>();
        for (Advertisement ad : storage.list()) {
            if (ad.getHits() > 0) copy.add(ad);
        }
        List<Advertisement> toPlay = selectVideosToDisplay(0, copy, timeSeconds);

        if (toPlay.isEmpty()) throw new NoVideoAvailableException();

        Collections.sort(toPlay, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                if (o2.getAmountPerOneDisplaying() != o1.getAmountPerOneDisplaying())
                    return Long.compare(o2.getAmountPerOneDisplaying(), o1.getAmountPerOneDisplaying());
                else {
                    long costOfo1 = o1.getAmountPerOneDisplaying() * 1000 / o1.getDuration();
                    long costOfo2 = o2.getAmountPerOneDisplaying() * 1000 / o2.getDuration();
                    return Long.compare(costOfo1, costOfo2);
                }
            }
        });


        long amount = 0;
        int totalDuration = 0;
        for (Advertisement ad : toPlay) {
            amount += ad.getAmountPerOneDisplaying();
            totalDuration += ad.getDuration();
        }
        StatisticEventManager.getInstance().register(new VideoSelectedEventDataRow(toPlay, amount, totalDuration));

        for (Advertisement ad : toPlay) {
            System.out.println(String.format("%s is displaying... %d, %d",
                    ad.getName(), ad.getAmountPerOneDisplaying(), ad.getAmountPerOneDisplaying() * 1000 / ad.getDuration()));
            ad.revalidate();
        }
    }
}