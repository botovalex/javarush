package com.javarush.test.level27.lesson06.task02;

/* Определяем порядок захвата монитора. Сложная.
Реализуйте логику метода isNormalLockOrder, который должен определять:
соответствует ли порядок synchronized блоков в методе someMethodWithSynchronizedBlocks - порядку
передаваемых в него аргументов. Должно выполняться условие:
для разных объектов o1 и o2 isNormalLockOrder(o1, o2) != isNormalLockOrder(o2, o1), для одинаковых объектов одинаковый результат
Метод main не участвует в тестировании.
*/
public class Solution {
    public void someMethodWithSynchronizedBlocks(Object obj1, Object obj2) {
        //следующие 4 строки в тестах имеют другую реализацию
        int lock1 = obj1.hashCode();
        int lock2 = obj2.hashCode();
        Object firstLock = lock1 > lock2 ? obj1 : obj2;
        Object secondLock = lock1 > lock2 ? obj2 : obj1;

        synchronized (firstLock) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {
            }

            synchronized (secondLock) {
                System.out.println(obj1 + " " + obj2);
            }
        }
    }

    public static boolean isNormalLockOrder(final Solution solution, final Object o1, final Object o2) throws Exception {
        //do something here
        Thread tr1 = new Thread() {
            @Override
            public void run() {
                try {
                    solution.someMethodWithSynchronizedBlocks(o1, o2);
                } catch (Exception e) {}
            }
        };
        Thread tr2 = new Thread() {
            @Override
            public void run() {
                try {
                    solution.someMethodWithSynchronizedBlocks(o2, o1);
                } catch (Exception e) {}
            }
        };
        System.out.println("start tr1");
        tr1.start();
        System.out.println("start tr2");
        tr2.start();
        return false;
    }

    public static void main(String[] args) throws Exception {
        final Solution solution = new Solution();
        final Object o1 = new Object();
        final Object o2 = new Object();

        new Thread() {
            @Override
            public void run() {
                try {
                    isNormalLockOrder(solution, o1, o2); //expected boolean b
                } catch (Exception ignored) {
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                try {
                    isNormalLockOrder(solution, o2, o1); //expected boolean !b
                } catch (Exception ignored) {
                }
            }
        }.start();
    }
}
