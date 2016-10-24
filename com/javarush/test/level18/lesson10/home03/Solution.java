package com.javarush.test.level18.lesson10.home03;

/* Два в одном
Считать с консоли 3 имени файла
Записать в первый файл содержимого второго файла, а потом дописать в первый файл содержимое третьего файла
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String file1 = br.readLine();
        String file2 = br.readLine();
        String file3 = br.readLine();
        br.close();

        FileInputStream reader1 = new FileInputStream(file2);
        FileInputStream reader2 = new FileInputStream(file3);
        FileOutputStream writer = new FileOutputStream(file1);
        while (reader1.available() > 0)
            writer.write(reader1.read());
        while (reader2.available() > 0)
            writer.write(reader2.read());

        reader1.close();
        reader2.close();
        writer.close();
    }
}


/*
d:\1.txt
d:\2.txt
d:\3.txt
 */