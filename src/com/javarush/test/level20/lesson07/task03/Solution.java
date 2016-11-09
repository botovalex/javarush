package com.javarush.test.level20.lesson07.task03;

import com.javarush.test.level17.lesson10.bonus01.Person;

import java.io.*;
import java.util.List;

/* Externalizable Person
Класс Person должен сериализоваться с помощью интерфейса Externalizable.
Подумайте, какие поля не нужно сериализовать.
Исправьте ошибку сериализации.
Сигнатуры методов менять нельзя.
*/
public class Solution {
    public static void main(String[] args) throws Exception{
        Person alex = new Person("Alex", "Botov", 28);
        alex.setFather(new Person("Jenya", "Botov", 56));
        alex.setMother(new Person("Vika", "Botova", 53));
        Person person = new Person("test", "test", 111);


        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("d:\\1.txt"));
        ObjectOutputStream testOutStream = new ObjectOutputStream(new FileOutputStream("d:\\2.txt"));
        outputStream.writeObject(alex);
        outputStream.close();
        testOutStream.writeObject(person);
        testOutStream.close();

        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("d:\\1.txt"));
        ObjectInputStream testInputStream = new ObjectInputStream(new FileInputStream("d:\\2.txt"));
        Person newAlex = (Person) inputStream.readObject();
        inputStream.close();
        Person newTestPerson = (Person) testInputStream.readObject();
        testInputStream.close();

        System.out.println(alex);
        System.out.println(newAlex);
        System.out.println();
        System.out.println(person);
        System.out.println(newTestPerson);


    }

    public static class Person implements Externalizable {
        private String firstName;
        private String lastName;
        private int age;
        private Person mother;
        private Person father;
        private List<Person> children;

        public Person(String firstName, String lastName, int age) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
        }

        public Person() {super();}

        @Override
        public String toString() {
            return "Person{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", age=" + age +
                    ", mother=" + mother +
                    ", father=" + father +
                    ", children=" + children +
                    '}';
        }

        public void setMother(Person mother) {
            this.mother = mother;
        }

        public void setFather(Person father) {
            this.father = father;
        }

        public void setChildren(List<Person> children) {
            this.children = children;
        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeChars(firstName);
            out.writeChars(lastName);
            out.writeObject(father);
            out.writeObject(mother);
            out.writeInt(age);
            out.writeObject(children);
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            firstName = (String) in.readLine();
            lastName = (String) in.readLine();
            father = (Person)in.readObject();
            mother = (Person)in.readObject();
            age = in.readInt();
            children = (List)in.readObject();
        }
    }
}
