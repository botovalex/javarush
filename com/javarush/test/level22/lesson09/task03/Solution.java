package com.javarush.test.level22.lesson09.task03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* Составить цепочку слов
В методе main считайте с консоли имя файла, который содержит слова, разделенные пробелом.
В методе getLine используя StringBuilder расставить все слова в таком порядке,
чтобы последняя буква данного слова совпадала с первой буквой следующего не учитывая регистр.
Каждое слово должно участвовать 1 раз.
Метод getLine должен возвращать любой вариант.
Слова разделять пробелом.
В файле не обязательно будет много слов.

Пример тела входного файла:
Киев Нью-Йорк Амстердам Вена Мельбурн

Результат:
Амстердам Мельбурн Нью-Йорк Киев Вена
*/
public class Solution {
    public static void main(String[] args) throws Exception{
        //...
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //String fileName = br.readLine();
        String fileName = "d:\\1.txt";
        br.close();

        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        ArrayList<String> list = new ArrayList<String>();
        while (reader.ready())
            Collections.addAll(list, reader.readLine().split(" "));
        reader.close();

        String[] buffer = new String[list.size()];
        list.toArray(buffer);

        StringBuilder result = getLine(buffer);
        System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words) {
        if (words == null || words.length == 0) return new StringBuilder();
        StringBuilder sb = new StringBuilder();
        ArrayList<String> list = new ArrayList<String>();
        Collections.addAll(list, words);

        StringBuilder temp = getSameFirstAndLastLetter(list);
        if (temp.length() == 0) return temp;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).substring(list.get(i).length() - 1).equalsIgnoreCase(temp.toString())) {
                sb.append(list.get(i)).append(" ");
                list.remove(i);
                break;
            }
        }
        while (list.size() > 1) {
            for (int i = 0; i < list.size(); ) {
                String wordI = list.get(i);
                if (wordI.substring(0, 1).equalsIgnoreCase(temp.toString())) {
                    sb.append(wordI).append(" ");
                    temp.delete(0, temp.length()).append(wordI.substring(wordI.length() - 1));
                    list.remove(i);
                    i = 0;
                } else i++;
            }
        }

        sb.deleteCharAt(sb.length() - 1);


        return sb;
    }

    private static StringBuilder getSameFirstAndLastLetter(List<String> list) {
        for (int i = 0; i < list.size(); i++)
            for (int j = 0; j < list.size(); j++) {
                if (list.get(i).substring(0, 1).equalsIgnoreCase(list.get(j).substring(list.get(j).length() - 1)))
                    return new StringBuilder(list.get(i).substring(0, 1));
            }
        return new StringBuilder();
    }
}
