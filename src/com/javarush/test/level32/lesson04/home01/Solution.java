package com.javarush.test.level32.lesson04.home01;

import java.io.*;

/* Читаем из потока
Реализуйте логику метода getAllDataFromInputStream. Он должен вернуть StringWriter, содержащий все данные из переданного потока.
Возвращаемый объект ни при каких условиях не должен быть null.
Метод main не участвует в тестировании.
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        StringWriter writer = getAllDataFromInputStream(new FileInputStream("d:\\1\\1.txt"));
        System.out.println(writer.toString());

        StringWriter writer1 = getAllDataFromInputStream(null);
        System.out.println(writer1.toString());

//        StringWriter writer2 = getAllDataFromInputStream(new FileInputStream("test.log"));
//        System.out.println(writer2.toString());
    }

    public static StringWriter getAllDataFromInputStream(InputStream is) throws IOException {
        StringWriter writer = new StringWriter();
        if (is == null) return writer;

        InputStreamReader reader = new InputStreamReader(is);
        int len;
        char[] buffer = new char[1024];
        while ((len = reader.read(buffer)) > 0) {
            writer.write(buffer, 0, len);
        }

        return writer;
    }
}
