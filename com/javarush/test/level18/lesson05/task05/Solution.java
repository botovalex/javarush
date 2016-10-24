package com.javarush.test.level18.lesson05.task05;

/* DownloadException
1 Считывать с консоли имена файлов.
2 Если файл меньше 1000 байт, то:
2.1 Закрыть потоки
2.2 выбросить исключение DownloadException
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws DownloadException, IOException {
        InputStreamReader streamReader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(streamReader);
        FileInputStream inputStream;
        while (true) {
            inputStream = new FileInputStream(br.readLine());
            if (inputStream.available() < 1000) {
                inputStream.close();
                streamReader.close();
                br.close();
                throw new DownloadException();
            }

            }

    }

    public static class DownloadException extends Exception{

    }
}
