package com.javarush.test.level27.lesson15.big01.statistic.event;

import java.util.Date;

/**
 * Created by barbudos on 03.09.2016.
 */
public interface EventDataRow {
    EventType getType();
    Date getDate();
    int getTime();
}


/*
Нам понадобятся еще некоторые методы.
4. В EventDataRow создайте методы
Date getDate(), реализация которого вернет дату создания записи
int getTime(), реализация которого вернет время - продолжительность
 */