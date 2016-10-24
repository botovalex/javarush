package com.javarush.test.level18.lesson10.home06;

/* Встречаемость символов
Программа запускается с одним параметром - именем файла, который содержит английский текст.
Посчитать частоту встречания каждого символа.
Отсортировать результат по возрастанию кода ASCII (почитать в инете). Пример: ','=44, 's'=115, 't'=116
Вывести на консоль отсортированный результат:
[символ1]  частота1
[символ2]  частота2
Закрыть потоки. Не использовать try-with-resources

Пример вывода:
, 19
- 7
f 361
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;

public class Solution {
    public static void main(String[] args) throws Exception{
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));

        while (reader.ready()) {
            char c = (char) reader.read();
            if (!map.containsKey(c)) {
                map.put(c, 1);
            } else {
                map.put(c, map.get(c) + 1);
            }
        }
        reader.close();

        char[] array = new char[map.size()];
        int index = 0;

        for (Character character : map.keySet()) {
            array[index] = character;
            index++;
        }

        Arrays.sort(array);

        for (char element : array) {
            System.out.println(element + " " + map.get(element));
        }


    }
}
