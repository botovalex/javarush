package com.javarush.test.level25.lesson02.home01;

import java.util.*;

public enum Column implements Columnable{
    Customer("Customer"),
    BankName("Bank Name"),
    AccountNumber("Account Number"),
    Amount("Available Amount");

    private String columnName;

    private static int[] realOrder;

    private Column(String columnName) {
        this.columnName = columnName;
    }



    /**
     * Задает новый порядок отображения колонок, который хранится в массиве realOrder.
     * realOrder[индекс в энуме] = порядок отображения; -1, если колонка не отображается.
     *
     * @param newOrder новая последовательность колонок, в которой они будут отображаться в таблице
     * @throws IllegalArgumentException при дубликате колонки
     */
    public static void configureColumns(Column... newOrder) {
        realOrder = new int[values().length];
        for (Column column : values()) {
            realOrder[column.ordinal()] = -1;
            boolean isFound = false;

            for (int i = 0; i < newOrder.length; i++) {
                if (column == newOrder[i]) {
                    if (isFound) {
                        throw new IllegalArgumentException("Column '" + column.columnName + "' is already configured.");
                    }
                    realOrder[column.ordinal()] = i;
                    isFound = true;
                }
            }
        }
    }

    /**
     * Вычисляет и возвращает список отображаемых колонок в сконфигурированом порядке (см. метод configureColumns)
     * Используется поле realOrder.
     *
     * @return список колонок
     */
    public static List<Column> getVisibleColumns() {
        checkRealOrder();
        Map columnMap = new TreeMap<Integer, Column>();
        Column[] colums = values();
        for (int i = 0; i < realOrder.length; i++) {
            if (realOrder[i] >= 0) columnMap.put(realOrder[i], colums[i]);
        }
        return new ArrayList<Column>(columnMap.values());
    }

    @Override
    public String getColumnName() {
        return this.columnName;
    }

    @Override
    public boolean isShown() {
        return realOrder[ordinal()] >= 0;
    }

    @Override
    public void hide() {
        checkRealOrder();
        int temp = realOrder[this.ordinal()];
        realOrder[this.ordinal()] = -1;
        for (int i = 0; i < realOrder.length; i++) {
            if (realOrder[i] != -1 && realOrder[i] > temp)
                realOrder[i]--;
        }
    }

    private static void checkRealOrder() {
        if (realOrder == null) {
            realOrder = new int[values().length];
            for (Column column : values())
                realOrder[column.ordinal()] = column.ordinal();
        }
    }
}

