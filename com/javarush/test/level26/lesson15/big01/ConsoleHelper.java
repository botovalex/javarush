package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

import static com.javarush.test.level26.lesson15.big01.CashMachine.RESOURCE_PATH;

public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static ResourceBundle res = ResourceBundle.getBundle(RESOURCE_PATH + "common_en");

    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException{
        String answer = "";
        try {
            answer = reader.readLine();
            if (answer.equalsIgnoreCase(res.getString("operation.EXIT")))
                throw new InterruptOperationException();
        } catch (IOException ignored) {}
        return answer;
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        writeMessage(res.getString("choose.currency.code"));
        String line;
        while (true){
            line = readString();
            if (line.trim().length() == 3) break;
            else writeMessage(res.getString("invalid.data"));
        }
        return line.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));
        String digits;
        while (true) {
            digits = readString();
            if (digits != null && digits.matches("^\\d+ \\d+$"))
                return digits.trim().split(" ");
            writeMessage(res.getString("invalid.data"));
        }
    }

    public static Operation askOperation() throws InterruptOperationException {
        writeMessage(res.getString("choose.operation"));
        writeMessage(res.getString("operation.INFO"));
        writeMessage(res.getString("operation.DEPOSIT"));
        writeMessage(res.getString("operation.WITHDRAW"));
        writeMessage(res.getString("operation.EXIT"));
        String line;
        Operation operation;
        while (true){
            line = readString();
            try {
                operation = Operation.getAllowableOperationByOrdinal(Integer.parseInt(line));
                break;
            } catch (IllegalArgumentException e){
                writeMessage(res.getString("invalid.data"));
            }
        }
        return operation;
    }

    public static void printExitMessage(){
        writeMessage(res.getString("the.end"));
    }
}



/*
    the.end=Terminated. Thank you for visiting JavaRush cash machine. Good luck.
    choose.operation=Please choose an operation desired or type 'EXIT' for exiting
    operation.INFO=INFO
    operation.DEPOSIT=DEPOSIT
    operation.WITHDRAW=WITHDRAW
    operation.EXIT=EXIT
    invalid.data=Please specify valid data.
    choose.currency.code=Please choose a currency code, for example USD
    choose.denomination.and.count.format=Please specify integer denomination and integer count. For example '10 3' means 30 %s
 */