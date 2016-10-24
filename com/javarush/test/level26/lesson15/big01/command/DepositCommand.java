package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.*;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.ResourceBundle;

import static com.javarush.test.level26.lesson15.big01.CashMachine.RESOURCE_PATH;

/**
 * Created by barbudos on 26.08.2016.
 */
class DepositCommand implements Command{
    private ResourceBundle res = ResourceBundle.getBundle(RESOURCE_PATH + "deposit_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String code = ConsoleHelper.askCurrencyCode();
        String[] nominal = ConsoleHelper.getValidTwoDigits(code);
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
        int denomination = Integer.parseInt(nominal[0]);
        int count = Integer.parseInt(nominal[1]);
        manipulator.addAmount(denomination, count);
        ConsoleHelper.writeMessage(String.format(res.getString("success.format"), denomination * count, code));
    }
}




/*
before=Depositing...
success.format=%d %s was deposited successfully
invalid.data=Please specify valid data.
 */