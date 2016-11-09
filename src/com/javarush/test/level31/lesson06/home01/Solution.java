package com.javarush.test.level31.lesson06.home01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* Добавление файла в архив
В метод main приходит список аргументов.
Первый аргумент - полный путь к файлу fileName.
Второй аргумент - путь к zip-архиву.
Добавить файл (fileName) внутрь архива в директорию 'new'.
Если в архиве есть файл с таким именем, то заменить его.

Пример входных данных:
C:/result.mp3
C:/pathToTest/test.zip

Файлы внутри test.zip:
a.txt
b.txt

После запуска Solution.main архив test.zip должен иметь такое содержимое:
new/result.mp3
a.txt
b.txt

Подсказка: нужно сначала куда-то сохранить содержимое всех энтри,
а потом записать в архив все энтри вместе с добавленным файлом.
Пользоваться файловой системой нельзя.
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) return;

        Path file = Paths.get(args[0]);
        ZipInputStream inputZip = new ZipInputStream(new FileInputStream(args[1]));


        boolean isZipContainFile = false;
        ArrayList<ZipEntry> zipEntries = new ArrayList<>();
        while (inputZip.available() != 0) {
            ZipEntry tempZipEntry = inputZip.getNextEntry();
            if (tempZipEntry != null) {
                zipEntries.add(tempZipEntry);
            } else inputZip.skip(tempZipEntry.getCompressedSize());
        }
        inputZip.close();

        ZipOutputStream outputZip = new ZipOutputStream(new FileOutputStream(args[1]));

        for (ZipEntry zipEntry : zipEntries) {
            if (zipEntry.getName().equals(file.getFileName().toString())) {
                outputZip.putNextEntry(new ZipEntry(file.getFileName().toString()));
                Files.copy(file, outputZip);
                isZipContainFile = true;
            } else {
                outputZip.putNextEntry(zipEntry);
            }
        }

        if (!isZipContainFile) {
            Path directory = Paths.get(file.getParent().toString(), "new");
            deleteFile(directory.toFile());
            Files.createDirectory(directory);
            Path newFile = Paths.get(directory.toString(), file.getFileName().toString());
//            Files.copy(file, newFile, StandardCopyOption.REPLACE_EXISTING);
            outputZip.putNextEntry(new ZipEntry(directory.getFileName().toString()));
            Files.copy(directory, outputZip);
            Files.copy(file, outputZip);
        }

        outputZip.close();
    }

    public static void deleteFile(File file) {
        if (!file.exists()) return;

        if (file.isDirectory())
            for (File f : file.listFiles())
                deleteFile(f);

        file.delete();
    }
}
