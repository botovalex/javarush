package com.javarush.test.level20.lesson07.task01;

import java.io.*;

/* Externalizable для апартаментов
Реализуйте интерфейс Externalizable для класса Apartment
Подумайте, какие поля не нужно сериализовать.
*/
public class Solution {
    public static void main(String[] args) throws Exception{
        Apartment apartment = new Apartment("Дом, милый дом", 61);
        Apartment test = new Apartment();
        System.out.println(apartment);
        System.out.println(test);
        System.out.println("================");

        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("D:\\1.txt"));
        ObjectOutputStream testStream = new ObjectOutputStream(new FileOutputStream("D:\\2.txt"));
        outputStream.writeObject(apartment);
        outputStream.close();
        testStream.writeObject(test);
        testStream.close();

        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("D:\\1.txt"));
        ObjectInputStream test1Stream = new ObjectInputStream(new FileInputStream("D:\\2.txt"));
        Apartment newApartment = (Apartment) inputStream.readObject();
        inputStream.close();
        Apartment newTestApartment = (Apartment) test1Stream.readObject();
        test1Stream.close();

        System.out.println(newApartment);
        System.out.println(newTestApartment);
    }

    public static class Apartment implements Externalizable{

        private String address;
        private int year;

        /**
         * Mandatory public no-arg constructor.
         */
        public Apartment() { super(); }

        public Apartment(String adr, int y) {
            address = adr;
            year = y;
        }

        /**
         * Prints out the fields. used for testing!
         */
        public String toString() {
            return("Address: " + address + "\n" + "Year: " + year);
        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeObject(address);
            out.writeInt(year);
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            address = (String) in.readObject();
            year = in.readInt();
        }
    }
}
