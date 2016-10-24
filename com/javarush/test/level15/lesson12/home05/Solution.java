package com.javarush.test.level15.lesson12.home05;

/* Перегрузка конструкторов
1. В классе Solution создайте по 3 конструктора для каждого модификатора доступа.
2. В отдельном файле унаследуйте класс SubSolution от класса Solution.
3. Внутри класса SubSolution создайте конструкторы командой Alt+Insert -> Constructors.
4. Исправьте модификаторы доступа конструкторов в SubSolution так, чтобы они соответствовали конструкторам класса Solution.
*/

public class Solution {
    public Solution() {}

    public Solution(int i) {}

    public Solution(double d) {}

    Solution(short s) {}

    Solution(Object o) {}

    Solution(Solution solution) {}

    protected Solution(String s) {}

    protected Solution(Number number) {}

    protected Solution(Integer integer) {}

    private Solution(boolean b) {}

    private Solution(byte b) {}

    private Solution(long l) {}
}

