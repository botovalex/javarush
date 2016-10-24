package com.javarush.test.level10.lesson11.home06;

/* Конструкторы класса Human
Напиши класс Human с 6 полями. Придумай и реализуй 10 различных конструкторов для него. Каждый конструктор должен иметь смысл.
*/

import java.util.ArrayList;

public class Solution
{
    public static void main(String[] args)
    {

    }

    public static class Human
    {
        //напишите тут ваши переменные и конструкторы
        private String name;
        private int age;
        private boolean sex;
        private Human marriedTo;
        private ArrayList<Human> children;
        private ArrayList<Human> parents;

        public Human(boolean sex) {
            this.sex = sex;
        }

        public Human(int age, boolean sex) {
            this.age = age;
            this.sex = sex;
        }

        public Human(String name, boolean sex) {
            this.name = name;
            this.sex = sex;
        }

        public Human(String name, int age, boolean sex) {
            this.name = name;
            this.age = age;
            this.sex = sex;
        }

        public Human (String name, int age, boolean sex, Human father, Human mother) {
            this.name = name;
            this.age = age;
            this.sex = sex;
            this.parents.add(father);
            this.parents.add(mother);
        }

        public Human(String name, int age, boolean sex, Human marriedTo) {
            this.name = name;
            this.age = age;
            this.sex = sex;
            this.marriedTo = marriedTo;
        }

        public Human(String name, boolean sex, Human marriedTo) {
            this.name = name;
            this.sex = sex;
            this.marriedTo = marriedTo;
        }

        public Human (String name, int age, Human child, boolean sex) {
            this.name = name;
            this.age = age;
            this.sex = sex;
            this.children.add(child);
        }

        public Human (String name, int age, boolean sex, Human father, Human mother, Human child) {
            this.name = name;
            this.age = age;
            this.sex = sex;
            this.parents.add(father);
            this.parents.add(mother);
            this.children.add(child);
        }

        public Human (String name, int age, Human marriedTo, boolean sex, Human father, Human mother) {
            this.name = name;
            this.age = age;
            this.sex = sex;
            this.marriedTo = marriedTo;
            this.parents.add(father);
            this.parents.add(mother);
        }
    }
}
