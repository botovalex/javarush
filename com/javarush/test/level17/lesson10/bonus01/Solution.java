package com.javarush.test.level17.lesson10.bonus01;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* CRUD
CrUD - Create, Update, Delete
Программа запускается с одним из следующих наборов параметров:
-c name sex bd
-u id name sex bd
-d id
-i id
Значения параметров:
name - имя, String
sex - пол, "м" или "ж", одна буква
bd - дата рождения в следующем формате 15/04/1990
-c  - добавляет человека с заданными параметрами в конец allPeople, выводит id (index) на экран
-u  - обновляет данные человека с данным id
-d  - производит логическое удаление человека с id
-i  - выводит на экран информацию о человеке с id: name sex (м/ж) bd (формат 15-Apr-1990)

id соответствует индексу в списке
Все люди должны храниться в allPeople
Используйте Locale.ENGLISH в качестве второго параметра для SimpleDateFormat

Пример параметров: -c Миронов м 15/04/1990
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

        switch (args[0]) {
            case "-c":
                Person person;
                if (args[2].equals("м"))
                    person = Person.createMale(args[1], dateFormat.parse(args[3]));
                else person = Person.createFemale(args[1], dateFormat.parse(args[3]));
                allPeople.add(person);
                System.out.println(allPeople.indexOf(person));
                break;
            case "-u" :
                Sex sex = null;
                if (args[3].equals("м"))
                    sex = Sex.MALE;
                else if (args[3].equals("ж"))
                    sex = Sex.FEMALE;
                if (sex == null) {
                    System.out.println("Неверный формат параметров");
                    break;
                } else {
                    int index = Integer.parseInt(args[1]);
                    allPeople.get(index).setName(args[2]);
                    allPeople.get(index).setSex(sex);
                    allPeople.get(index).setBirthDay(dateFormat.parse(args[4]));
                }
                break;
            case "-d" :
                allPeople.get(Integer.parseInt(args[1])).setBirthDay(null);
                break;
            case "-i" :
                Person pers = allPeople.get(Integer.parseInt(args[1]));
                SimpleDateFormat dateOutputFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
                StringBuffer buffer = new StringBuffer();
                char c;
                if (pers.getSex() == Sex.MALE) c = 'м'; else c = 'ж';
                buffer.append(pers.getName()).append(" ").append(c).append(" ").append(dateOutputFormat.format(pers.getBirthDay()));
                System.out.println(buffer.toString());
                break;
            default:
                System.out.println("Неверный формат параметров");
        }


    }
}


/*
        SimpleDateFormat dateOutputFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        for (Person person : allPeople) {
            System.out.print(person.getName() + " === ");
            System.out.print(person.getSex() + " === ");
            System.out.print(dateFormat.format(person.getBirthDay()) + " === ");
            System.out.println(dateOutputFormat.format(person.getBirthDay()));
        }
 */