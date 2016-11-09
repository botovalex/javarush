package com.javarush.test.level20.lesson02.task02;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* Читаем и пишем в файл: JavaRush
Реализуйте логику записи в файл и чтения из файла для класса JavaRush
В файле your_file_name.tmp может быть несколько объектов JavaRush
Метод main реализован только для вас и не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File your_file_name = File.createTempFile("your_file_name", null);
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            JavaRush javaRush = new JavaRush();
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут
            javaRush.users.add(new User());
            javaRush.users.add(new User());

            User user1 = javaRush.users.get(0);
            User user2 = javaRush.users.get(1);
            user1.setFirstName("fn1");
            user2.setFirstName("fn2");
            user1.setLastName("ln1");
            user2.setLastName("ln2");
            user1.setBirthDate(new Date());
            long l = Long.parseLong("1466012736601");
            user2.setBirthDate(new Date(l));
            user1.setMale(true);
            user2.setMale(false);
            user1.setCountry(User.Country.RUSSIA);
            user2.setCountry(User.Country.UKRAINE);



            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //check here that javaRush object equals to loadedObject object - проверьте тут, что javaRush и loadedObject равны

            outputStream.close();
            inputStream.close();

            user1.printUser();
            loadedObject.users.get(0).printUser();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод

            PrintWriter writer = new PrintWriter(outputStream);
            for (User user : users) {
                String line = "newUser";
                writer.println(line);

                line = user.getFirstName() != null ? "exists" : "notExists";
                writer.println(line);
                if (user.getFirstName() != null) writer.println(user.getFirstName());

                line = user.getLastName() != null ? "exists" : "notExists";
                writer.println(line);
                if (user.getLastName() != null) writer.println(user.getLastName());

                line = user.getBirthDate() != null ? "exists" : "notExists";
                writer.println(line);
                if (user.getBirthDate() != null) writer.println(user.getBirthDate().getTime());

                writer.println(user.isMale());

                line = user.getCountry() != null ? "exists" : "notExists";
                writer.println(line);
                if (user.getCountry() != null) writer.println(user.getCountry());
            }
            writer.flush();

        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            while (reader.ready()) {
                User user;
                if (reader.readLine().equals("newUser")) {
                    user = new User();

                    if (reader.readLine().equals("exists"))
                        user.setFirstName(reader.readLine());

                    if (reader.readLine().equals("exists"))
                        user.setLastName(reader.readLine());

                    if (reader.readLine().equals("exists")) {
                        long l = Long.parseLong(reader.readLine());
                        user.setBirthDate(new Date(l));
                    }

                    boolean sex = reader.readLine().equals("true");
                    user.setMale(sex);

                    if (reader.readLine().equals("exists")) {
                        User.Country country;
                        switch (reader.readLine().toUpperCase()) {
                            case "RUSSIA" :
                                country = User.Country.RUSSIA;
                                break;
                            case "UKRAINE" :
                                country = User.Country.UKRAINE;
                                break;
                            default:
                                country = User.Country.OTHER;
                        }
                        user.setCountry(country);
                    }
                    users.add(user);

                }

            }
        }
    }
}
