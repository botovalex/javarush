package com.javarush.test.level27.lesson15.big01.statistic;

import com.javarush.test.level27.lesson15.big01.statistic.event.CookedOrderEventDataRow;
import com.javarush.test.level27.lesson15.big01.statistic.event.EventDataRow;
import com.javarush.test.level27.lesson15.big01.statistic.event.EventType;
import com.javarush.test.level27.lesson15.big01.statistic.event.VideoSelectedEventDataRow;

import java.util.*;

/**
 * Created by barbudos on 03.09.2016.
 */
public class StatisticEventManager {
    private final static StatisticEventManager ourInstance = new StatisticEventManager();
    private StatisticStorage statisticStorage = new StatisticStorage();

    public static StatisticEventManager getInstance() {
        return ourInstance;
    }

    private StatisticEventManager() {}

    public void register(EventDataRow data) {
        if (data != null)
            statisticStorage.put(data);
    }

    public Map<Date, Float> getVideoSelectedAmount() {
        Map<Date, Float> result = new TreeMap<>(Collections.<Date>reverseOrder());
        ArrayList<EventDataRow> videos = new ArrayList<>(statisticStorage.get(EventType.SELECTED_VIDEOS));

        for (EventDataRow event : videos) {
            VideoSelectedEventDataRow video = (VideoSelectedEventDataRow) event;

            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(video.getDate());
            calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
            calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
            calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
            calendar.set(Calendar.MILLISECOND, calendar.getActualMinimum(Calendar.MILLISECOND));
            Date date = calendar.getTime();

            Float amount = (float) video.getAmount() / 100;
            result.put(date, result.containsKey(date) ? result.get(date) + amount : amount);
        }
        return result;
    }

    public Map<Date, TreeMap<String, Integer>> getCookedOrder() {
        Map<Date, TreeMap<String, Integer>> result = new TreeMap<>(Collections.<Date>reverseOrder());
        ArrayList<EventDataRow> cookedOrders = new ArrayList<>(statisticStorage.get(EventType.COOKED_ORDER));

        for (EventDataRow cookedOrder : cookedOrders) {
            CookedOrderEventDataRow order = (CookedOrderEventDataRow) cookedOrder;
            Integer cookingTimeSeconds = order.getTime();
            if (cookingTimeSeconds <= 0) continue;

            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(order.getDate());
            calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
            calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
            calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
            calendar.set(Calendar.MILLISECOND, calendar.getActualMinimum(Calendar.MILLISECOND));
            Date date = calendar.getTime();

            String cookName = order.getCookName();
            if (!result.containsKey(date)) {
                TreeMap<String, Integer> map = new TreeMap<>();
                map.put(cookName, cookingTimeSeconds);
                result.put(date, map);
            } else {
                TreeMap<String, Integer> map = result.get(date);
                map.put(cookName, map.containsKey(cookName) ? map.get(cookName) + cookingTimeSeconds : cookingTimeSeconds);
                result.put(date, map);
            }
        }

        return result;
    }

    private class StatisticStorage {
        private Map<EventType, List<EventDataRow>> events;

        private StatisticStorage() {
            events = new HashMap<>();
            for (EventType eventType : EventType.values()) {
                events.put(eventType, new ArrayList<EventDataRow>());
            }
        }

        private void put(EventDataRow data){
            events.get(data.getType()).add(data);
        }

        private List<EventDataRow> get(EventType type) {
            return events.get(type);
        }
    }
}