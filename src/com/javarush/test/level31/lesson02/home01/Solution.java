package com.javarush.test.level31.lesson02.home01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.TreeMap;

/* Проход по дереву файлов
1. На вход метода main подаются два параметра.
Первый - path - путь к директории, второй - resultFileAbsolutePath - имя файла, который будет содержать результат.
2. Для каждого файла в директории path и в ее всех вложенных поддиректориях выполнить следующее:
2.1. Если у файла длина в байтах больше 50, то удалить его.
2.2. Если у файла длина в байтах НЕ больше 50, то для всех таких файлов:
2.2.1. отсортировать их по имени файла в возрастающем порядке, путь не учитывать при сортировке
2.2.2. переименовать resultFileAbsolutePath в 'allFilesContent.txt'
2.2.3. в allFilesContent.txt последовательно записать содержимое всех файлов из п. 2.2.1. Тела файлов разделять "\n"
2.3. Удалить директории без файлов (пустые).
Все файлы имеют расширение txt.
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) return;
        File path = new File(args[0]);
        File resultFileAbsolutePath = new File(args[1]);

        TreeMap<String, File> files = new TreeMap<>();
        if (path.isDirectory() && !path.equals(resultFileAbsolutePath)) files.putAll(walkFileTree(path));
        if (files.size() > 0) {
            FileOutputStream writer = new FileOutputStream(resultFileAbsolutePath, false);
            Iterator<File> it = files.values().iterator();
            while (it.hasNext()) {
                FileInputStream reader = new FileInputStream(it.next());
                byte[] buffer = new byte[reader.available()];
                reader.read(buffer);
                writer.write(buffer);
                if (it.hasNext()) {
                    writer.write('\r');
                    writer.write('\n');
                }
                reader.close();
            }
            writer.close();

            File resultFile = new File(resultFileAbsolutePath.getParent(), "allFilesContent.txt");
            if (resultFile.exists()) resultFile.delete();
            resultFileAbsolutePath.renameTo(resultFile);

        }
    }

    private static TreeMap<String, File> walkFileTree(File folder) {
        TreeMap<String, File> result = new TreeMap<>();
        if (folder.listFiles().length != 0) {
            for (File file : folder.listFiles()) {
                if (file.isDirectory()) {
                    result.putAll(walkFileTree(file));
                }
                else if (file.isFile()) {
                    if (file.length() > 50) file.delete();
                    else result.put(file.getName(), file);
                }
            }
        }
        for (File file : folder.listFiles()) {
            if (file.isDirectory() && file.listFiles().length == 0)
                file.delete();
        }
        return result;
    }
}
