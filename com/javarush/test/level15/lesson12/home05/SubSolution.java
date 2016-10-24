package com.javarush.test.level15.lesson12.home05;

/**
 * Created by barbudos on 18.06.2016.
 */
public class SubSolution extends Solution {
    public SubSolution() {
    }

    public SubSolution(int i) {
        super(i);
    }

    public SubSolution(double d) {
        super(d);
    }

    SubSolution(short s) {
        super(s);
    }

    SubSolution(Object o) {
        super(o);
    }

    SubSolution(Solution solution) {
        super(solution);
    }

    protected SubSolution(String s) {
        super(s);
    }

    protected SubSolution(Number number) {
        super(number);
    }

    protected SubSolution(Integer integer) {
        super(integer);
    }

    private SubSolution(boolean b) {}

    private SubSolution(byte b) {}

    private SubSolution(long l) {}
}
