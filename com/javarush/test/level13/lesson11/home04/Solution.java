package com.javarush.test.level13.lesson11.home04;

/* Запись в файл
1. Прочесть с консоли имя файла.
2. Считывать строки с консоли, пока пользователь не введет строку "exit".
3. Вывести абсолютно все введенные строки в файл, каждую строчку с новой стороки.
*/

import java.io.*;
import java.util.ArrayList;

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        /*BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name = br.readLine();

        ArrayList<String> list = new ArrayList<String>();
        String line;
        while (!(line = br.readLine()).equals("exit"))
            list.add(line);
        list.add(line);

        File fout = new File(name);
        FileWriter writer = new FileWriter(fout, true);
        for (int i = 0; i < list.size() - 1; i++) {
            writer.write(list.get(i));
            writer.append(String.format("%n"));
        }
        writer.write(list.get(list.size() - 1));
        writer.close();
        */
        //System.out.print(String.format("%n"));
        //System.out.println("sdfds");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(reader.readLine())));

        String s;

        while (!(s = reader.readLine()).equals("exit")) {
            writer.write(s);
            writer.newLine();
        }
        writer.write(s);

        reader.close();
        writer.close();


    }
}
