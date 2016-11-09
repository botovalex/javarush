package com.javarush.test.level05.lesson12.home02;

/* Man and Woman
1. Внутри класса Solution создай public static классы Man и Woman.
2. У классов должны быть поля: name(String), age(int), address(String).
3. Создай конструкторы, в которые передаются все возможные параметры.
4. Создай по два объекта каждого класса со всеми данными используя конструктор.
5. Объекты выведи на экран в таком формате [name + " " + age + " " + address].
*/

public class Solution
{
    public static void main(String[] args)
    {
        Man man1 = new Man("Рагнар", 40, "Дания");
        Man man2 = new Man("Ролло", 35, "Франция");
        Woman woman1 = new Woman("Лагерта", 30, "Дания");
        Woman woman2 = new Woman("Хельга", 25, "Дания");

        man1.printMan();
        man2.printMan();
        woman1.printWoman();
        woman2.printWoman();
    }

    public static class Man
    {
        String name;
        int age;
        String address;

        public Man(String name, String address)
        {
            this.name = name;
            this.address = address;
            this.age = 25;
        }

        public Man(String name)
        {
            this.name = name;
            this.address = null;
            this.age = 25;
        }

        public Man(String name, int age)
        {
            this.name = name;
            this.address = null;
            this.age = age;
        }

        public Man(String name, int age, String address)
        {
            this.name = name;
            this.address = address;
            this.age = age;
        }

        public void printMan()
        {
            System.out.println(name + " " + age + " " + address);
        }
    }

    public static class Woman
    {
        String name;
        int age;
        String address;

        public Woman(String name, String address)
        {
            this.name = name;
            this.address = address;
            this.age = 25;
        }

        public Woman(String name)
        {
            this.name = name;
            this.address = null;
            this.age = 25;
        }

        public Woman(String name, int age)
        {
            this.name = name;
            this.address = null;
            this.age = age;
        }

        public Woman(String name, int age, String address)
        {
            this.name = name;
            this.address = address;
            this.age = age;
        }

        public void printWoman()
        {
            System.out.println(name + " " + age + " " + address);
        }
    }
}
