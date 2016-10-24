package com.javarush.test.level08.lesson11.home06;

/* Вся семья в сборе
1. Создай класс Human с полями имя (String), пол (boolean), возраст (int), дети (ArrayList<Human>).
2. Создай объекты и заполни их так, чтобы получилось: два дедушки, две бабушки, отец, мать, трое детей.
3. Вывести все объекты Human на экран.
*/

import java.util.ArrayList;

public class Solution
{
    public static void main(String[] args)
    {
        ArrayList<Human> people = new ArrayList<Human>();

        Human son1 = new Human("Саша", true, 28);
        Human dau1 = new Human("Оля", false, 27);
        Human dau2 = new Human("Аня", false, 23);
        people.add(son1);
        people.add(dau1);
        people.add(dau2);

        Human father = new Human("Женя", true, 56);
        father.setChildren(son1);
        father.setChildren(dau1);
        father.setChildren(dau2);
        Human mother = new Human("Вика", false, 53);
        mother.setChildren(son1);
        mother.setChildren(dau1);
        mother.setChildren(dau2);
        people.add(father);
        people.add(mother);


        Human grandFather = new Human("Саша", true, 80);
        grandFather.setChildren(father);
        Human grandMother = new Human("Женя", false, 75);
        grandMother.setChildren(father);
        people.add(grandFather);
        people.add(grandMother);

        Human grandFather1 = new Human("Гриша", true, 80);
        grandFather1.setChildren(mother);
        Human grandMother1 = new Human("Лора", false, 75);
        grandMother1.setChildren(mother);
        people.add(grandFather1);
        people.add(grandMother1);

        for (Human human : people)
            System.out.println(human);

    }

    public static class Human
    {
        String name;
        boolean sex;
        int age;
        ArrayList<Human> children = new ArrayList<Human>();

        public Human(String name, boolean sex, int age) {
            this.name = name;
            this.sex = sex;
            this.age = age;
        }

        public void setChildren (Human children) {
            this.children.add(children);
        }

        public String toString()
        {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            int childCount = this.children.size();
            if (childCount > 0)
            {
                text += ", дети: "+this.children.get(0).name;

                for (int i = 1; i < childCount; i++)
                {
                    Human child = this.children.get(i);
                    text += ", "+child.name;
                }
            }

            return text;
        }
    }

}
