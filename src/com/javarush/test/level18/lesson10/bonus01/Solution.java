package com.javarush.test.level18.lesson10.bonus01;

/* Шифровка
Придумать механизм шифровки/дешифровки

Программа запускается с одним из следующих наборов параметров:
-e fileName fileOutputName
-d fileName fileOutputName
где
fileName - имя файла, который необходимо зашифровать/расшифровать
fileOutputName - имя файла, куда необходимо записать результат шифрования/дешифрования
-e - ключ указывает, что необходимо зашифровать данные
-d - ключ указывает, что необходимо расшифровать данные
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception{
        switch (args[0]) {
            case "-e" :
                encrypt(args[1], args[2]);
                break;
            case "-d" :
                decrypt(args[1], args[2]);
                break;
        }
    }

    public static void encrypt(String fileName, String fileOutputName) throws Exception {
        InputStream reader = new FileInputStream(fileName);
        OutputStream writer = new FileOutputStream(fileOutputName);

        while (reader.available() > 0) {
            writer.write(reader.read() + 127);
            writer.write((int) Math.round(Math.random() * 100 % 255));
        }

        reader.close();
        writer.close();
    }

    public static void decrypt(String fileName, String fileOutputName) throws Exception{
        InputStream reader = new FileInputStream(fileName);
        OutputStream writer = new FileOutputStream(fileOutputName);

        while (reader.available() > 0) {
            writer.write(reader.read() - 127);
            reader.read();
        }

        reader.close();
        writer.close();
    }

}
