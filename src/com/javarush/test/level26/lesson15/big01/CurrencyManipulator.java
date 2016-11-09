package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;

import java.util.*;

/**
 * Created by barbudos on 25.08.2016.
 */
public class CurrencyManipulator {
    private String currencyCode;
    private Map<Integer, Integer> denominations;    //номинал, количество

    public String getCurrencyCode() {
        return currencyCode;
    }

    public boolean hasMoney() {
        return !denominations.isEmpty();
    }

    public boolean isAmountAvailable(int expectedAmount){
        return getTotalAmount() >= expectedAmount;
    }

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
        denominations = new HashMap<Integer, Integer>();
    }

    public void addAmount(int denomination, int count) {
        Integer current = denominations.get(denomination);
        if (current == null)
            denominations.put(denomination, count);
        else denominations.put(denomination, current + count);
    }

    public int getTotalAmount() {
        int sum = 0;
        for (Map.Entry<Integer, Integer> entry : denominations.entrySet()) {
            sum += entry.getKey() * entry.getValue();
        }
        return sum;
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        TreeMap<Integer, Integer> result = new TreeMap<>(Collections.<Integer>reverseOrder());
        Integer[] nominals = denominations.keySet().toArray(new Integer[denominations.size()]);
        Arrays.sort(nominals, Collections.<Integer>reverseOrder());
        int index = 0;
        int tempAmount = expectedAmount;
        HashMap<Integer, Integer> copyDen = new HashMap<>(denominations);
        while (true) {
            try {
                while (index < nominals.length) {
                    for (int i = index; i < nominals.length; ) {
                        if (nominals[i] > tempAmount){
                            i++;
                            continue;
                        }
                        if (nominals[i] <= tempAmount && copyDen.get(nominals[i]) > 0) {
                            tempAmount -= nominals[i];
                            result.put(nominals[i], result.get(nominals[i]) == null ? 1 : result.get(nominals[i]) + 1);
                            copyDen.put(nominals[i], copyDen.get(nominals[i]) - 1);
                        } else i++;
                        if (tempAmount == 0) break;
                    }
                    if (tempAmount != 0) {
                        tempAmount = expectedAmount;
                        result.clear();
                        copyDen.clear();
                        copyDen.putAll(denominations);
                        index++;
                    }
                    else break;
                }

                if (tempAmount != 0)
                    throw new NotEnoughMoneyException();
                else {
                    for (Map.Entry<Integer, Integer> entry : result.entrySet()) {
                        denominations.put(entry.getKey(), denominations.get(entry.getKey()) - entry.getValue());
                    }
                }

            } catch (ConcurrentModificationException ignored){}
            break;
        }

        return result;
    }

}


/*
        TreeMap<Integer, Integer> result = new TreeMap<>(Collections.<Integer>reverseOrder());
        Integer[] nominals = denominations.keySet().toArray(new Integer[denominations.size()]);
        Arrays.sort(nominals, Collections.<Integer>reverseOrder());
        int index = 0;
        int tempAmount = expectedAmount;
        HashMap<Integer, Integer> copyDen = new HashMap<>(denominations);
        while (true) {
            try {
                while (index < nominals.length) {
                    for (int i = index; i < nominals.length; ) {
                        if (nominals[i] > tempAmount){
                            i++;
                            continue;
                        }
                        if (nominals[i] <= tempAmount && copyDen.get(nominals[i]) > 0) {
                            tempAmount -= nominals[i];
                            result.put(nominals[i], result.get(nominals[i]) == null ? 1 : result.get(nominals[i]) + 1);
                            copyDen.put(nominals[i], copyDen.get(nominals[i]) - 1);
                        } else i++;
                        if (tempAmount == 0) break;
                    }
                    if (tempAmount != 0) {
                        tempAmount = expectedAmount;
                        result.clear();
                        copyDen.clear();
                        copyDen.putAll(denominations);
                        index++;
                    }
                    else break;
                }

                if (tempAmount != 0)
                    throw new NotEnoughMoneyException();
                else
                    for (Map.Entry<Integer, Integer> entry : copyDen.entrySet())
                        denominations.put(entry.getKey(), denominations.get(entry.getKey()) - entry.getValue());
            } catch (ConcurrentModificationException ignored){}
            break;
        }

        return result;
 */