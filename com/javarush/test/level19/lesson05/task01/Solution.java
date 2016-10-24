package com.javarush.test.level19.lesson05.task01;

/* Четные байты
Считать с консоли 2 имени файла.
Вывести во второй файл все байты с четным индексом.
Пример: второй байт, четвертый байт, шестой байт и т.д.
Закрыть потоки ввода-вывода.
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String fileInpute = br.readLine();
        String fileOutput = br.readLine();
        FileReader reader = new FileReader(fileInpute);
        FileWriter writer = new FileWriter(fileOutput);
        boolean isEven = false;
        while (reader.ready()) {
            int data = reader.read();
            if (isEven)
                writer.write(data);
            if (isEven) isEven = false;
            else isEven = true;
        }
        br.close();
        reader.close();
        writer.close();

    }
}
