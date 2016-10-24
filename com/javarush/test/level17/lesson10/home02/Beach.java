package com.javarush.test.level17.lesson10.home02;

/* Comparable
Реализуйте интерфейс Comparable<Beach> в классе Beach, который будет использоваться нитями.
*/

public class Beach implements Comparable<Beach>{
    private volatile String name;      //название
    private volatile float distance;   //расстояние
    private volatile int quality;    //качество

    public Beach(String name, float distance, int quality) {
        this.name = name;
        this.distance = distance;
        this.quality = quality;
    }

    public String getName() {
        return name;
    }

    public synchronized void setName(String name) {
        this.name = name;
    }

    public float getDistance() {
        return distance;
    }

    public synchronized void setDistance(float distance) {
        this.distance = distance;
    }

    public int getQuality() {
        return quality;
    }

    public synchronized void setQuality(int quality) {
        this.quality = quality;
    }

    @Override
    public synchronized int compareTo(Beach o) {
        return (int) (((int) 100 * (distance - o.getDistance())) + (quality - o.getQuality()));
    }

    public static void main(String[] args) {
        Beach b1 = new Beach("b1", 3, 6);
        Beach b2 = new Beach("b2", 3, 6);
        Beach b3 = new Beach("b3", 4, 6);
        Beach b4 = new Beach("b4", 1, 9);
        System.out.println(b1.compareTo(b2));
        System.out.println(b1.compareTo(b3));
        System.out.println(b1.compareTo(b4));
    }
}
