package com.javarush.test.level18.lesson10.bonus02;

/* Прайсы
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается со следующим набором параметров:
-c productName price quantity
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-c  - добавляет товар с заданными параметрами в конец файла, генерирует id самостоятельно, инкрементируя максимальный id, найденный в файле

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины

Пример:
19846   Шорты пляжные синие           159.00  12
198478  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String fileName = br.readLine();
        br.close();

        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));

        switch (args[0]) {
            case "-c" :
                int id = 0;
                while (reader.ready()) {
                    String line = reader.readLine();
                    int nextID = Integer.parseInt(line.substring(0, 8).trim());
                    if (id < nextID) id = nextID;
                }
                id++;
                StringBuilder lineToWrite = new StringBuilder();
                lineToWrite.append(setTheSize(String.valueOf(id), 8)).
                            append(setTheSize(args[1], 30)).
                            append(setTheSize(args[2], 8)).
                            append(setTheSize(args[3], 4));
                //writer.newLine();
                writer.write(lineToWrite.toString());
                break;
        }


        reader.close();
        writer.close();
    }

    public static String setTheSize(String line, int size) {
        if (size < 0) return "";
        if (line.length() == size) return line;
        if (line.length() > size) return line.substring(0, size);

        StringBuilder builder = new StringBuilder(line);
        for (int i = line.length(); i < size; i++) {
            builder.append(" ");
        }
        return builder.toString();
    }
}
