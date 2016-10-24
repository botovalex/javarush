package com.javarush.test.level26.lesson02.task03;

import java.util.Comparator;

/* Убежденному убеждать других не трудно.
В таблице есть колонки, по которым можно сортировать.
Пользователь имеет возможность настроить под себя список колонок, которые будут сортироваться.
Напишите public static компаратор CustomizedComparator, который будет:
1. в конструкторе принимать список компараторов
2. сортировать данные в порядке, соответствующем последовательности компараторов.
Все переданные компараторы сортируют дженерик тип Т
В конструктор передается как минимум один компаратор
*/
public class Solution {
    public static class CustomizedComparator implements Comparator {
        private Comparator[] comparators;

        public CustomizedComparator(Comparator... comparators) {
            this.comparators = comparators;
        }

        @Override
        public int compare(Object o1, Object o2) {
            int rezult = comparators[0].compare(o1, o2);
            for (int i = 0; i < comparators.length; i++) {
                rezult = comparators[i].compare(o1, o2);
                if (rezult != 0) return rezult;
            }
            return rezult;
        }
    }

    public static void main(String[] args) {
        Comparator c = new CustomizedComparator(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        }, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        });
    }
}
