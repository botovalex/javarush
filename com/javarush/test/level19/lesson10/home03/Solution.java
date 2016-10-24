package com.javarush.test.level19.lesson10.home03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/* Хуан Хуанович
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя день месяц год
где [имя] - может состоять из нескольких слов, разделенных пробелами, и имеет тип String
[день] - int, [месяц] - int, [год] - int
данные разделены пробелами

Заполнить список PEOPLE импользуя данные из файла
Закрыть потоки. Не использовать try-with-resources

Пример входного файла:
Иванов Иван Иванович 31 12 1987
Вася 15 5 2013
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        while (reader.ready()) {
            String[] tempArray = reader.readLine().split(" ");
            StringBuilder name = new StringBuilder();
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < tempArray.length; i++) {
                if (i < tempArray.length - 3)
                    name.append(tempArray[i]).append(" ");
                else list.add(Integer.parseInt(tempArray[i]));
            }
            Calendar birthday = new GregorianCalendar(list.get(2), list.get(1) - 1, list.get(0));
            PEOPLE.add(new Person(name.toString().trim(), birthday.getTime()));
        }
        reader.close();
    }

}
