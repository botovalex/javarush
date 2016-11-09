package com.javarush.test.level21.lesson08.task03;

/* Запретить клонирование
Разрешите клонировать класс А
Запретите клонировать класс B
Разрешите клонировать класс C
Метод main не участвует в тестировании.
*/
public class Solution {
    public static class A implements Cloneable {
        private int i;
        private int j;

        public A(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }

        public A clone() throws CloneNotSupportedException{
            return new A(i, j);
        }
    }

    public static class B extends A implements Cloneable{
        private String name;

        public B(int i, int j, String name) {
            super(i, j);
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public B clone() throws CloneNotSupportedException{
            throw new CloneNotSupportedException();
        }
    }

    public static class C extends B implements Cloneable{
        public C(int i, int j, String name) {
            super(i, j, name);
        }

        public C clone() throws CloneNotSupportedException {
            return new C(super.getI(), super.getJ(), super.name != null ? super.name : null);
        }
    }

    public static void main(String[] args) {
        A a = new A(1, 2);
        B b = new B(3, 4, "B");
        C c = new C(5, 6, "C");
        A cloneA = null;
        B cloneB = null;
        C cloneC = null;

        try {
            cloneA = a.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("A не может быть clone");
        }
        try {
            cloneB = b.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("B не может быть clone");
        }
        try {
            cloneC = c.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("C не может быть clone");
        }

        System.out.println(a);
        System.out.println(cloneA);
        System.out.println();
        System.out.println(b);
        System.out.println(cloneB);
        System.out.println();
        System.out.println(c);
        System.out.println(cloneC);
    }
}
