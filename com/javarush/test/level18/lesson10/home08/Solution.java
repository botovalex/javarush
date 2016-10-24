package com.javarush.test.level18.lesson10.home08;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/* Нити и байты
Читайте с консоли имена файлов, пока не будет введено слово "exit"
Передайте имя файла в нить ReadThread
Нить ReadThread должна найти байт, который встречается в файле максимальное число раз, и добавить его в словарь resultMap,
где параметр String - это имя файла, параметр Integer - это искомый байт.
Закрыть потоки. Не использовать try-with-resources
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while (!(line = br.readLine()).equals("exit")) {
            Thread thread = new ReadThread(line);
            thread.start();
            thread.join();
        }

    }

    public static class ReadThread extends Thread {

        public ReadThread(String fileName) {
            //implement constructor body
            super(fileName);
        }
        // implement file reading here - реализуйте чтение из файла тут

        @Override
        public void run() {
            try {
                FileInputStream reader = new FileInputStream(this.getName());
                HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
                while (reader.available() > 0) {
                    int nextByte = reader.read();
                    if (map.containsKey(nextByte)) {
                        map.put(nextByte, map.get(nextByte) + 1);
                    } else map.put(nextByte, 1);
                }
                reader.close();
                int max = 0;
                Integer resultByte = -1;
                for (Map.Entry<Integer, Integer> entry : map.entrySet())
                    if (entry.getValue() > max) {
                        max = entry.getValue();
                        resultByte = entry.getKey();
                    }
                synchronized (resultMap) {
                    resultMap.put(this.getName(), resultByte);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}


/*
d:\1.txt
d:\2.txt
d:\3.txt
exit
 */

/*
d:\2.txt – 98
d:\3.txt – 99
d:\1.txt – 97
 */

/*
        for (Map.Entry<String, Integer> s : resultMap.entrySet())
            System.out.println(s.getKey() + " – " + s.getValue());
 */