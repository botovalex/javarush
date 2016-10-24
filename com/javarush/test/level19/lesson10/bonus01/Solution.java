package com.javarush.test.level19.lesson10.bonus01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* Отслеживаем изменения
Считать в консоли 2 имени файла - file1, file2.
Файлы содержат строки, file2 является обновленной версией file1, часть строк совпадают.
Нужно создать объединенную версию строк, записать их в список lines
Операции ADDED и REMOVED не могут идти подряд, они всегда разделены SAME
Пример:
оригинальный   редактированный    общий
file1:         file2:             результат:(lines)

строка1        строка1            SAME строка1
строка2                           REMOVED строка2
строка3        строка3            SAME строка3
строка4                           REMOVED строка4
строка5        строка5            SAME строка5
строка0                           ADDED строка0
строка1        строка1            SAME строка1
строка2                           REMOVED строка2
строка3        строка3            SAME строка3
строка5                           ADDED строка5
строка4        строка4            SAME строка4
строка5                           REMOVED строка5
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws Exception{
        /*
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String file1Name = br.readLine();
        String file2Name = br.readLine();
        br.close();
        */
        String file1Name = "d:\\1.txt";
        String file2Name = "d:\\2.txt";

        BufferedReader reader1 = new BufferedReader(new FileReader(file1Name));
        BufferedReader reader2 = new BufferedReader(new FileReader(file2Name));

        ArrayList<String> list1 = new ArrayList<String>();
        ArrayList<String> list2 = new ArrayList<String>();
        while (reader1.ready())
            list1.add(reader1.readLine());
        while (reader2.ready())
            list2.add(reader2.readLine());
        reader1.close();
        reader2.close();

        int size = list1.size() > list2.size() - 1 ? list1.size() : list2.size();
        for (int i = 0; i < size; i++) {
            String temp1 = i < list1.size() ? list1.get(i) : "";
            String temp2 = i < list2.size() ? list2.get(i) : "";
            String temp21 = (i + 1) < list2.size() ? list2.get(i + 1) : "";
            if (temp1.equals(temp2))
                lines.add(new LineItem(Type.SAME, temp1));
            else if (temp1.equals(temp21))
                    lines.add(new LineItem(Type.ADDED, temp21));
                else lines.add(new LineItem(Type.REMOVED, temp1));
        }

        for (LineItem line : lines) {
            System.out.println(line);
        }


    }


    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        @Override
        public String toString() {
            return type + " " + line;
        }

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}
