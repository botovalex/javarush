package com.javarush.test.level19.lesson03.task05;

import java.util.HashMap;
import java.util.Map;

/* Закрепляем адаптер
Адаптировать Customer и Contact к RowItem.
Классом-адаптером является DataAdapter.
Инициализируйте countries перед началом выполнения программы. Соответствие кода страны и названия:
UA Ukraine
RU Russia
CA Canada
*/

public class Solution {
    private static Map<String,String> countries = new HashMap<String,String>();


    static {
        countries.put("UA", "Ukraine");
        countries.put("RU", "Russia");
        countries.put("CA", "Canada");
    }


    public static class DataAdapter implements RowItem{
        private Customer customer;
        private Contact contact;

        public DataAdapter(Customer customer, Contact contact) {
            this.customer = customer;
            this.contact = contact;
        }

        @Override
        public String getCountryCode() {
            String countryName = this.customer.getCountryName();
            for (Map.Entry<String, String> entry : countries.entrySet()) {
                if (entry.getValue().equals(countryName))
                    return entry.getKey();
            }
            return null;
        }

        @Override
        public String getCompany() {
            return this.customer.getCompanyName();
        }

        @Override
        public String getContactFirstName() {
            String line = this.contact.getName();
            return line.substring(line.lastIndexOf(", ") + 2);
        }

        @Override
        public String getContactLastName() {
            String line = this.contact.getName();
            return line.substring(0, line.lastIndexOf(", "));
        }

        @Override
        public String getDialString() {
            char[] buffer = this.contact.getPhoneNumber().toCharArray();
            StringBuilder builder = new StringBuilder("callto://");
            for (char c : buffer) {
                if (Character.isDigit(c) || c == '+')
                    builder.append(c);
            }
            return builder.toString();
        }
    }

    public static interface RowItem {
        String getCountryCode();        //example UA
        String getCompany();            //example JavaRush Ltd.
        String getContactFirstName();   //example Ivan
        String getContactLastName();    //example Ivanov
        String getDialString();         //example callto://+380501234567
    }

    public static interface Customer {
        String getCompanyName();        //example JavaRush Ltd.
        String getCountryName();        //example Ukraine
    }

    public static interface Contact {
        String getName();               //example Ivanov, Ivan
        String getPhoneNumber();        //example +38(050)123-45-67
    }
}


/*
        countries.put("UA", "Ukraine");
        countries.put("RU", "Russia");
        countries.put("CA", "Canada");
 */