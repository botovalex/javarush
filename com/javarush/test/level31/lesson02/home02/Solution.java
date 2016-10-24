package com.javarush.test.level31.lesson02.home02;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/* Находим все файлы
Реализовать логику метода getFileTree, который должен в директории root найти список всех файлов включая вложенные.
Используйте очередь, рекурсию не используйте.
Верните список всех путей к найденным файлам, путь к директориям возвращать не надо.
Путь должен быть абсолютный.
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        PriorityQueue<File> queue = new PriorityQueue<>();
        ArrayList<String> result = new ArrayList<>();

        if (root == null) return result;
        File path = new File(root);
        if (!path.exists() || path.isFile()) return result;

        for (File file : path.listFiles()) {
            if (file.isDirectory()) queue.offer(file);
            else result.add(file.getAbsolutePath());
        }

        File directory;
        while ((directory = queue.poll()) != null) {
            for (File file : directory.listFiles()) {
                if (file.isDirectory()) queue.offer(file);
                else result.add(file.getAbsolutePath());
            }
        }

        return result;
    }

}
