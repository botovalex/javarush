package com.javarush.test.level19.lesson03.task04;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

/* И еще один адаптер
Адаптировать Scanner к PersonScanner.
Классом-адаптером является PersonScannerAdapter.
Данные в файле хранятся в следующем виде:
Иванов Иван Иванович 31 12 1950

В файле хранится большое количество людей, данные одного человека находятся в одной строке. Метод read() должен читать данные одного человека.
*/

public class Solution {
    public static class PersonScannerAdapter implements PersonScanner{
        private Scanner scanner;

        public PersonScannerAdapter(Scanner scanner) {
            this.scanner = scanner;
        }

        @Override
        public Person read() throws IOException {
            String[] line = this.scanner.hasNext() ? this.scanner.nextLine().split(" ") : null;
            if (line != null) {
                Calendar calendar = new GregorianCalendar(Integer.parseInt(line[5]), (Integer.parseInt(line[4]) - 1), Integer.parseInt(line[3]));
                return new Person(line[1], line[2], line[0], calendar.getTime());
            }
            return null;
        }

        @Override
        public void close() throws IOException {
            this.scanner.close();
        }
    }
}
