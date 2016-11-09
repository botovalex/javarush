package com.javarush.test.level32.lesson15.big01;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class HTMLFileFilter extends FileFilter {
    @Override
    public boolean accept(File f) {
        if (f.isDirectory()) return true;
        return f.getName().substring(f.getName().length() - 5).equalsIgnoreCase(".html") ||
                f.getName().substring(f.getName().length() - 4).equalsIgnoreCase(".htm");
    }

    @Override
    public String getDescription() {
        return "HTML и HTM файлы";
    }
}

/*
21.1.	Создай публичный класс HTMLFileFilter унаследованный от FileFilter.
21.2.	Мы хотим, чтобы окно выбора файла отображало только html/htm файлы или папки.
Переопредели метод accept(File file), чтобы он возвращал true, если переданный файл
директория или содержит в конце имени ".html" или ".htm" без учета регистра.
21.3.	Чтобы в окне выбора файла в описании доступных типов файлов отображался текст
"HTML и HTM файлы" переопредели соответствующим образом метод getDescription().
 */