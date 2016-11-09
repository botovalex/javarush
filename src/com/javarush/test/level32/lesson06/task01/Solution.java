package com.javarush.test.level32.lesson06.task01;

import java.io.ByteArrayOutputStream;

/* Генератор паролей
Реализуйте логику метода getPassword, который должен возвращать ByteArrayOutputStream, в котором будут байты пароля.
Требования к паролю:
1) 8 символов
2) только цифры и латинские буквы разного регистра
3) обязательно должны присутствовать цифры, и буквы разного регистра
Все сгенерированные пароли должны быть уникальные.
Пример правильного пароля:
wMh7SmNu
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
        ByteArrayOutputStream password1 = getPassword();
        System.out.println(password1.toString());
        ByteArrayOutputStream password2 = getPassword();
        System.out.println(password2.toString());
        ByteArrayOutputStream password3 = getPassword();
        System.out.println(password3.toString());
        ByteArrayOutputStream password4 = getPassword();
        System.out.println(password4.toString());
        ByteArrayOutputStream password5 = getPassword();
        System.out.println(password5.toString());
        ByteArrayOutputStream password6 = getPassword();
        System.out.println(password6.toString());
        ByteArrayOutputStream password7 = getPassword();
        System.out.println(password7.toString());
        ByteArrayOutputStream password8 = getPassword();
        System.out.println(password8.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        ByteArrayOutputStream result = new ByteArrayOutputStream();

        boolean isaz = false;
        boolean isAZ = false;
        boolean isDig = false;

        for (int i = 0; i < 5; i++) {
            int num = (int) (Math.random() * 3);
            switch (num) {
                case 0:
                    isDig = true;
                    break;
                case 1:
                    isAZ = true;
                    break;
                case 2:
                    isaz = true;
                    break;
            }
            result.write(getRandomChar(num));
        }

        if (!isDig) result.write(getRandomChar(0));
        else result.write(getRandomChar((int) (Math.random() * 3)));

        if (!isAZ) result.write(getRandomChar(1));
        else result.write(getRandomChar((int) (Math.random() * 3)));

        if (!isaz) result.write(getRandomChar(2));
        else result.write(getRandomChar((int) (Math.random() * 3)));

        return result;
    }

    private static int getRandomChar(int i) {
        switch (i) {
            case 0:
                return (int)(Math.random() * 10 + 48);
            case 1:
                return (int)(Math.random() * 26 + 65);
            case 2:
                return (int)(Math.random() * 26 + 97);
        }
        return 0;
    }
}

/*
09      48 - 57     isDig
AZ      65 - 90     isAZ
az      97 - 122    isaz


 */