package com.javarush.test.level21.lesson05.task01;

import java.util.HashSet;
import java.util.Set;

/* Equals and HashCode
В классе Solution исправить пару методов equals/hashCode в соответствии с правилами реализации этих методов.
Метод main не участвует в тестировании.
*/
public class Solution {
    private final String first, last;

    public Solution(String first, String last) {
        this.first = first;
        this.last = last;
    }
/*
    public boolean equals(Solution n) {
        return n.first.equals(first) && n.last.equals(last);
    }

    public int hashCode() {
        return 31 * first.hashCode() + last.hashCode();
    }
*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Solution)) return false;

        Solution solution = (Solution) o;

        if (first != null ? !first.equals(solution.first) : solution.first != null) return false;
        return last != null ? last.equals(solution.last) : solution.last == null;

    }

    @Override
    public int hashCode() {
        int result = first != null ? first.hashCode() : 0;
        result = 31 * result + (last != null ? last.hashCode() : 0);
        return result;
    }

    public static void main(String[] args) {
        Set<Solution> s = new HashSet<>();
        s.add(new Solution("Liska", "Botova"));
        System.out.println(s.contains(new Solution("Liska", "Botova")));
        System.out.println();

        Solution test1 = new Solution("Liska", "Botova");
        Solution test2 = new Solution("Liska", "Botova");
        System.out.println(test1.equals(test2));
        System.out.println("test1.hashcode " + test1.hashCode());
        System.out.println("test2.hashcode " + test2.hashCode());
        s.add(test1);
        System.out.println(s.contains(test2));

    }
}
