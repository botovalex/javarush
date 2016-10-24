package com.javarush.test.level22.lesson05.task01;

/* Найти подстроку
Метод getPartOfString должен возвращать подстроку начиная с символа после 1-го пробела и до конца слова,
которое следует после 4-го пробела.
Пример: "JavaRush - лучший сервис обучения Java."
Результат: "- лучший сервис обучения"
На некорректные данные бросить исключение TooShortStringException (сделать исключением).
Сигнатуру метода getPartOfString не менять.
Метод main не участвует в тестировании.
*/
public class Solution {
    public static void main(String[] args) throws Exception{
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
        System.out.println(getPartOfString(" 1 2 3 4 5 1 1 1 1 1 1 1 1"));
        System.out.println(getPartOfString(null));
    }

    public static String getPartOfString(String string) throws TooShortStringException{
        StringBuilder sb = new StringBuilder();
        String result;
        try {
            String[] temp = string.split(" ");
            result = sb.append(temp[1]).append(" ").
                        append(temp[2]).append(" ").
                        append(temp[3]).append(" ").
                        append(temp[4]).toString();
        } catch (Exception e) {
            throw new TooShortStringException();
        }

        return result;
    }

    public static class TooShortStringException extends Exception{
    }
}
