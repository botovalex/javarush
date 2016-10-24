package com.javarush.test.level20.lesson02.task05;

import java.io.*;

/* И еще раз о синхронизации
Разберитесь почему не работает метод main()
Реализуйте логику записи в файл и чтения из файла для класса Object
Метод load должен инициализировать объект данными из файла
Метод main реализован только для вас и не участвует в тестировании
*/
public class Solution {
    public static void main(java.lang.String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            //File your_file_name = File.createTempFile("your_file_name", null);
            File your_file_name = new File("D:\\1.txt");
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            Object object = new Object();
            object.string1 = new String();   //string #1
            object.string2 = new String();   //string #2
            object.save(outputStream);
            outputStream.flush();

            Object loadedObject = new Object();
            loadedObject.string1 = new String(); //string #3
            loadedObject.string2 = new String(); //string #4


            loadedObject.load(inputStream);
            //check here that the object variable equals to loadedObject - проверьте тут, что object и loadedObject равны
            /*
            System.out.println(object.string1 + " +++++ " + loadedObject.string1 + " ===== " + object.string1.equals(loadedObject.string1));
            System.out.println(object.string2 + " +++++ " + loadedObject.string2 + " ===== " + object.string2.equals(loadedObject.string2));
            */
            object.string1.print();
            object.string2.print();
            loadedObject.string1.print();
            loadedObject.string2.print();


            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }


    public static class Object {
        public String string1;
        public String string2;

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод
            PrintWriter writer = new PrintWriter(outputStream);
            PrintStream consoleStream = System.out;
            ByteArrayOutputStream outputSystemStream = new ByteArrayOutputStream();
            PrintStream stream = new PrintStream(outputSystemStream);
            System.setOut(stream);
            string1.print();
            java.lang.String resultString1 = outputSystemStream.toString();
            outputSystemStream.reset();
            string2.print();
            java.lang.String resultString2 = outputSystemStream.toString();
            System.setOut(consoleStream);

            writer.print(resultString1);
            writer.print(resultString2);
            writer.flush();
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            java.lang.String resultString1 = reader.readLine();
            java.lang.String resultString2 = reader.readLine();
            inputStream.close();
            reader.close();

            int string1number = Integer.parseInt(resultString1.substring(resultString1.lastIndexOf("#") + 1));
            int string2number = Integer.parseInt(resultString2.substring(resultString2.lastIndexOf("#") + 1));

            int temp = countStrings;
            countStrings = --string1number;
            string1 = new String();
            countStrings = --string2number;
            string2 = new String();
            countStrings = temp;
        }
    }

    public static int countStrings;

    public static class String {
        private final int number;

        public String() {
            number = ++countStrings;
        }

        public void print() {
            System.out.println("string #" + number);
        }
    }
}
