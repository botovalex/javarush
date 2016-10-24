package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.ad.StatisticAdvertisementManager;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticEventManager;

import java.util.*;

/**
 * Created by barbudos on 03.09.2016.
 */
public class DirectorTablet {
    public void printAdvertisementProfit() {
        TreeMap<Date, Float> videoSelectedAmount = new TreeMap<>(StatisticEventManager.getInstance().getVideoSelectedAmount());
        Float totalAmount = 0F;
        for (Map.Entry<Date, Float> entry : videoSelectedAmount.entrySet()) {
            totalAmount += entry.getValue();
            ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "%td-%<tb-%<tY - %.2f", entry.getKey(), entry.getValue()));
        }
        ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "Total - %.2f", totalAmount));
    }

    public void printCookWorkloading() {
        TreeMap<Date, TreeMap<String, Integer>> cookedOrders = new TreeMap<>(StatisticEventManager.getInstance().getCookedOrder());

        for (Map.Entry<Date, TreeMap<String, Integer>> entry : cookedOrders.entrySet()) {
            ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "%td-%<tb-%<tY", entry.getKey()));
            TreeMap<String, Integer> map = entry.getValue();
            for (Map.Entry<String, Integer> mapEntry : map.entrySet()) {
                float duration = mapEntry.getValue();
                ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "%s - %d min", mapEntry.getKey(), (int) Math.ceil(duration / 60)));
            }
            ConsoleHelper.writeMessage("");
        }

    }

    public void printActiveVideoSet() {
        TreeMap<String, Integer> activeVideos = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }
        });
        activeVideos.putAll(StatisticAdvertisementManager.getInstance().getAdvertisementVideo(true));

        for (Map.Entry<String, Integer> entry : activeVideos.entrySet()) {
            ConsoleHelper.writeMessage(entry.getKey() + " - " + entry.getValue());
        }
    }

    public void printArchivedVideoSet() {
        TreeMap<String, Integer> activeVideos = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }
        });
        activeVideos.putAll(StatisticAdvertisementManager.getInstance().getAdvertisementVideo(false));

        for (String s : activeVideos.keySet()) {
            ConsoleHelper.writeMessage(s);
        }
    }

    public static void main(String[] args) {
        DirectorTablet tab = new DirectorTablet();
        tab.printActiveVideoSet();
    }
}


/*
Пример вывода для printActiveVideoSet:
First Video - 100
second video - 10
Third Video - 2
четвертое видео - 4

Через 50 показов пример вывода для printArchivedVideoSet:
second video
Third Video
четвертое видео
 */