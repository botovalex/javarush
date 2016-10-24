package com.javarush.test.level18.lesson10.home10;

/* Собираем файл
Собираем файл из кусочков
Считывать с консоли имена файлов
Каждый файл имеет имя: [someName].partN. Например, Lion.avi.part1, Lion.avi.part2, ..., Lion.avi.part37.
Имена файлов подаются в произвольном порядке. Ввод заканчивается словом "end"
В папке, где находятся все прочтенные файлы, создать файл без приставки [.partN]. Например, Lion.avi
В него переписать все байты из файлов-частей используя буфер.
Файлы переписывать в строгой последовательности, сначала первую часть, потом вторую, ..., в конце - последнюю.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;
import java.util.HashMap;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<Integer, String> map = new HashMap<Integer, String>();
        String line;
        while (!(line = br.readLine()).equals("end")) {
            String fileName = line.substring(0, line.lastIndexOf('.'));
            int partNumber = Integer.parseInt(line.substring(line.lastIndexOf(".part") + 5));
            map.put(partNumber, fileName);
        }
        br.close();

        BufferedWriter writer = new BufferedWriter(new FileWriter(map.get(1)));
        for (int i = 1; i <= map.size(); i++) {
            StringBuilder builder = new StringBuilder();
            builder.append(map.get(i)).append(".part").append(i);
            BufferedReader reader = new BufferedReader(new FileReader(builder.toString()));
            while (reader.ready())
                writer.write(reader.read());
            reader.close();
        }
        writer.close();
    }
}


/*
D:\1.txt.part1
D:\1.txt.part2
D:\1.txt.part3
end
 */