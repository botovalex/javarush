package com.javarush.test.level26.lesson02.task01;

import java.util.Arrays;
import java.util.Comparator;

/* Почитать в инете про медиану выборки
Реализовать логику метода sort, который должен сортировать данные в массиве по удаленности от его медианы
Вернуть отсортированный массив от минимального расстояния до максимального
Если удаленность одинаковая у нескольких чисел, то выводить их в порядке возрастания
*/
public class Solution {
    public static Integer[] sort(Integer[] array) {
        //implement logic here
        if (array == null) return null;
        if (array.length == 1) return array;

        Arrays.sort(array);
        final int mediana;
        if (array.length % 2 == 1)
            mediana = array[array.length / 2];
        else
            mediana = (array[array.length / 2] + array[array.length / 2 - 1]) / 2;
        Arrays.sort(array, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 == o2) return 0;

                int div1 = Math.abs(o1 - mediana);
                int div2 = Math.abs(o2 - mediana);

                return div1 - div2 != 0 ? div1 - div2 : o1 - o2;
            }
        });
        return array;
    }

}
