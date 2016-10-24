package com.javarush.test.level07.lesson12.home06;

/* Семья
Создай класс Human с полями имя(String), пол(boolean),возраст(int), отец(Human), мать(Human).
Создай объекты и заполни их так, чтобы получилось: Два дедушки, две бабушки, отец, мать, трое детей. Вывести объекты на экран.
Примечание:
Если написать свой метод String toString() в классе Human, то именно он будет использоваться при выводе объекта на экран.
Пример вывода:
Имя: Аня, пол: женский, возраст: 21, отец: Павел, мать: Катя
Имя: Катя, пол: женский, возраст: 55
Имя: Игорь, пол: мужской, возраст: 2, отец: Михаил, мать: Аня
…
*/

public class Solution
{
    public static void main(String[] args)
    {
        Human grandFather1 = new Human("дед1", true, 55, null, null);
        Human grandFather2 = new Human("дед2", true, 65, null, null);
        Human grandMa1 = new Human("баб1", false, 55, null, null);
        Human grandMa2 = new Human("баб2", false, 65, null, null);
        Human papa1 = new Human("папа1", true, 45, grandFather1, grandMa1);
        Human mama1 = new Human("мама1", false, 40, grandFather2, grandMa2);
        Human son1 = new Human("сын1", true, 25, papa1, mama1);
        Human dau1 = new Human("дочь1", false, 20, papa1, mama1);
        Human dau2 = new Human("дочь2", false, 15, papa1, mama1);

        System.out.println(grandFather1);
        System.out.println(grandFather2);
        System.out.println(grandMa1);
        System.out.println(grandMa2);
        System.out.println(papa1);
        System.out.println(mama1);
        System.out.println(son1);
        System.out.println(dau1);
        System.out.println(dau2);
    }

    public static class Human
    {
        String name;
        boolean sex;
        int age;
        Human father;
        Human mother;

        public Human(String name, boolean sex, int age, Human father, Human mother) {
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.father = father;
            this.mother = mother;
        }

        public String toString()
        {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            if (this.father != null)
                text += ", отец: " + this.father.name;

            if (this.mother != null)
                text += ", мать: " + this.mother.name;

            return text;
        }
    }

}
