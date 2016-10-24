package com.javarush.test.level19.lesson10.home02;

/* Самый богатый
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя значение
где [имя] - String, [значение] - double. [имя] и [значение] разделены пробелом

Для каждого имени посчитать сумму всех его значений
Вывести в консоль имена, у которых максимальная сумма
Имена разделять пробелом либо выводить с новой строки
Закрыть потоки. Не использовать try-with-resources

Пример входного файла:
Петров 0.501
Иванов 1.35
Петров 0.85

Пример вывода:
Петров
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        HashMap<String, Double> map = new HashMap<String, Double>();
        while (reader.ready()) {
            String[] temp = reader.readLine().split(" ");
            if (!map.containsKey(temp[0]))
                map.put(temp[0], Double.parseDouble(temp[1]));
            else
                map.put(temp[0], map.get(temp[0]) + Double.parseDouble(temp[1]));
        }
        reader.close();

        double max = 0.0;
        for (Double aDouble : map.values())
            if (max < aDouble) max = aDouble;

        for (Map.Entry<String, Double> m : map.entrySet())
            if (m.getValue() == max)
                System.out.println(m.getKey());

    }
}
