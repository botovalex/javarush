package com.javarush.test.level27.lesson15.big01.statistic.event;

import com.javarush.test.level27.lesson15.big01.ad.Advertisement;

import java.util.Date;
import java.util.List;

/**
 * Created by barbudos on 03.09.2016.
 */
public class VideoSelectedEventDataRow implements EventDataRow {
    private List<Advertisement> optimalVideoSet;
    private long amount;
    private int totalDuration;
    private Date currentDate;

    public VideoSelectedEventDataRow(List<Advertisement> optimalVideoSet, long amount, int totalDuration) {
        this.optimalVideoSet = optimalVideoSet;
        this.amount = amount;
        this.totalDuration = totalDuration;
        this.currentDate = new Date();
    }

    @Override
    public EventType getType() {
        return EventType.SELECTED_VIDEOS;
    }

    @Override
    public Date getDate() {
        return currentDate;
    }

    public long getAmount() {
        return amount;
    }

    @Override
    public int getTime() {
        return totalDuration;
    }
}


/*
2.3. VideoSelectedEventDataRow(List<Advertisement> optimalVideoSet, long amount, int totalDuration)
optimalVideoSet - список видео-роликов, отобранных для показа
amount - сумма денег в копейках
totalDuration - общая продолжительность показа отобранных рекламных роликов
 */