package com.javarush.test.level13.lesson11.home03;

/* Чтение файла
1. Считать с консоли имя файла.
2. Вывести в консоль(на экран) содержимое файла.
3. Не забыть освободить ресурсы. Закрыть поток чтения с файла и поток ввода с клавиатуры.
*/

import java.io.*;

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        InputStreamReader in = new InputStreamReader((System.in));
        BufferedReader br = new BufferedReader(in);
        String name = br.readLine();
        File fileInpute = new File(name);
        BufferedReader fin = new BufferedReader(new FileReader(fileInpute));
        String line;
        while ((line = fin.readLine()) != null)
            System.out.println(line);


        br.close();
        in.close();

    }
}
