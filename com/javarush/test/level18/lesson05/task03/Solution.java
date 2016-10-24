package com.javarush.test.level18.lesson05.task03;

/* Разделение файла
Считать с консоли три имени файла: файл1, файл2, файл3.
Разделить файл1 по следующему критерию:
Первую половину байт записать в файл2, вторую половину байт записать в файл3.
Если в файл1 количество байт нечетное, то файл2 должен содержать бОльшую часть.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String file1 = br.readLine();
        String file2 = br.readLine();
        String file3 = br.readLine();
        br.close();

        FileInputStream inputFile1 = new FileInputStream(file1);
        FileOutputStream outputFile2 = new FileOutputStream(file2);
        FileOutputStream outputFile3 = new FileOutputStream(file3);
        byte[] byteArray = new byte[inputFile1.available()];
        int count = inputFile1.read(byteArray);
        if (count % 2 == 0) {
            outputFile2.write(byteArray, 0, count / 2);
            outputFile3.write(byteArray, count / 2, byteArray.length - count / 2);
        } else {
            outputFile2.write(byteArray, 0, count / 2 + 1);
            outputFile3.write(byteArray, count / 2 + 1, byteArray.length - count / 2 - 1);
        }


        inputFile1.close();
        outputFile2.close();
        outputFile3.close();

    }
}

/*
d:\1.txt
d:\2.txt
d:\3.txt
 */