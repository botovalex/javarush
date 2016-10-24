package com.javarush.test.level20.lesson02.task03;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/* Знакомство с properties
В методе fillInPropertiesMap считайте имя файла с консоли и заполните карту properties данными из файла.
Про .properties почитать тут - http://ru.wikipedia.org/wiki/.properties
Реализуйте логику записи в файл и чтения из файла для карты properties.
*/
public class Solution {
    public static Map<String, String> properties = new HashMap<>();

    /*
    public static void main(String[] args) throws Exception {
        Solution solution = new Solution();
        solution.fillInPropertiesMap();
        System.out.println(properties);
        FileOutputStream outputStream = new FileOutputStream("d:\\1.txt");
        solution.save(outputStream);
        System.out.println(properties);
        FileInputStream inputStream = new FileInputStream("d:\\1.txt");
        solution.load(inputStream);
        System.out.println(properties);
    }
    */

    public void fillInPropertiesMap() throws IOException{
        //implement this method - реализуйте этот метод
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(br.readLine())));
        br.close();

        Properties pr = new Properties();
        pr.load(reader);
        Set<String> keys = pr.stringPropertyNames();
        for (String key : keys) {
            properties.put(key, pr.getProperty(key));
        }

        reader.close();
    }

    public void save(OutputStream outputStream) throws Exception {
        //implement this method - реализуйте этот метод
        PrintWriter writer = new PrintWriter(outputStream);
        Properties pr = new Properties();
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            pr.setProperty(entry.getKey(), entry.getValue());
        }
        pr.store(outputStream, null);
        writer.close();

    }

    public void load(InputStream inputStream) throws Exception {
        //implement this method - реализуйте этот метод
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        Properties pr = new Properties();
        pr.load(reader);
        Set<String> keys = pr.stringPropertyNames();
        for (String key : keys) {
            properties.put(key, pr.getProperty(key));
        }

        reader.close();

    }
}
