package com.javarush.test.level18.lesson10.bonus03;

/* Прайсы 2
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается с одним из следующих наборов параметров:
-u id productName price quantity
-d id
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-u  - обновляет данные товара с заданным id
-d  - производит физическое удаление товара с заданным id (все данные, которые относятся к переданному id)

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины

Пример:
19846   Шорты пляжные синие           159.00  12
198478  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String fileName = br.readLine();
        br.close();

        RandomAccessFile file = new RandomAccessFile(fileName, "rw");
        int separatorLength = System.getProperty("line.separator").length();


        switch (args[0]) {
            case "-u" :
                file.seek(0);
                while (file.getFilePointer() < file.length()) {
                    String line = file.readLine();
                    if (line.substring(0, 8).trim().equals(args[1])) {
                        if (file.getFilePointer() == file.length())
                            file.seek(file.getFilePointer() - line.length());
                        else file.seek(file.getFilePointer() - line.length() - separatorLength);
                        
                        StringBuilder lineToWrite = new StringBuilder();
                        lineToWrite.append(setTheSize(args[1], 8)).
                                append(setTheSize(args[2], 30)).
                                append(setTheSize(args[3], 8)).
                                append(setTheSize(args[4], 4));
                        file.writeBytes(lineToWrite.toString());
                        if (file.getFilePointer() != file.length())
                            file.writeBytes(System.getProperty("line.separator"));
                    }
                }
                break;
            case "-d" :
                file.seek(0);
                while (file.getFilePointer() < file.length()) {
                    long startPointer = file.getFilePointer();
                    String line = file.readLine();
                    long tempPointer = file.getFilePointer();
                    if (args[1].equals(line.substring(0, 8).trim())) {
                        while (startPointer < file.length()) {
                            if (((file.length() - line.length()) == startPointer)
                                    || ((file.length() - line.length() - separatorLength) == startPointer))
                                file.setLength(startPointer - separatorLength);
                            else {
                                file.seek(startPointer + line.length() + separatorLength);
                                String tempLine = file.readLine();
                                file.seek(startPointer);
                                if (tempLine != null)
                                    file.writeBytes(tempLine);
                                startPointer += (line.length() + separatorLength);
                            }
                        }
                        file.seek(tempPointer);
                    }
                }
                break;

        }
        file.close();
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