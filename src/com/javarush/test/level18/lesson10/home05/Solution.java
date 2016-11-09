package com.javarush.test.level18.lesson10.home05;

/* Округление чисел
Считать с консоли 2 имени файла
Первый файл содержит вещественные(дробные) числа, разделенные пробелом. Например, 3.1415
Округлить числа до целых и записать через пробел во второй файл
Закрыть потоки. Не использовать try-with-resources
Принцип округления:
3.49 - 3
3.50 - 4
3.51 - 4
-3.49 - -3
-3.50 - -3
-3.51 - -4
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String file1 = br.readLine();
        String file2 = br.readLine();
        br.close();

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file1)));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file2)));
        ArrayList<String> list = new ArrayList<String>();
        ArrayList<Float> floatList = new ArrayList<Float>();

        while (reader.ready()) {
            list.add(reader.readLine());
        }
        reader.close();

        for (String s : list) {
            for (String s1 : s.split(" ")) {
                floatList.add(Float.parseFloat(s1));
            }
        }

        for (Float aFloat : floatList) {
            StringBuilder builder = new StringBuilder();
            builder.append(Math.round(aFloat)).append(" ");
            writer.write(builder.toString());
        }
        writer.close();

    }
}


/*
d:\1.txt
d:\2.txt
 */