package com.javarush.test.level18.lesson10.home04;

/* Объединение файлов
Считать с консоли 2 имени файла
В начало первого файла записать содержимое второго файла так, чтобы получилось объединение файлов
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String file1 = br.readLine();
        String file2 = br.readLine();
        br.close();

        FileInputStream reader = new FileInputStream(file2);
        RandomAccessFile writer = new RandomAccessFile(file1, "rw");
        writer.seek(0);
        byte[] backup = new byte[(byte) writer.length()];
        writer.read(backup);
        byte[] buffer = new byte[reader.available()];
        int count = reader.read(buffer);
        writer.seek(0);
        writer.write(buffer, 0, count);
        writer.write(backup);
        reader.close();
        writer.close();



        /*
        FileInputStream bufferReader = new FileInputStream(file1);
        byte[] buffer = new byte[bufferReader.available()];
        int count = bufferReader.read(buffer);
        bufferReader.close();

        FileInputStream reader = new FileInputStream(file2);
        FileOutputStream writer = new FileOutputStream(file1);
        while (reader.available() > 0)
            writer.write(reader.read());
        writer.write(buffer, 0, count);

        reader.close();
        writer.close();
        */

    }
}

/*
d:\1.txt
d:\2.txt
 */