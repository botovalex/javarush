package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;
import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;

import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

import static com.javarush.test.level26.lesson15.big01.CashMachine.RESOURCE_PATH;

/**
 * Created by barbudos on 26.08.2016.
 */
class WithdrawCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(RESOURCE_PATH + "withdraw_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String code = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator man = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
        int amount = 0;
        while (true) {

            while (true){
                try {
                    ConsoleHelper.writeMessage(res.getString("specify.amount"));
                    amount = Integer.parseInt(ConsoleHelper.readString());
                    break;
                } catch (IllegalArgumentException e) {
                    ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                }
            }


            if (!man.isAmountAvailable(amount)) {
                ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                continue;
            }

            try {
                TreeMap<Integer, Integer> map = (TreeMap<Integer, Integer>) man.withdrawAmount(amount);
                for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                    ConsoleHelper.writeMessage(String.format("\t%d - %d", entry.getKey(), entry.getValue()));
                }
                ConsoleHelper.writeMessage(String.format(res.getString("success.format"), amount, code));
                break;
            } catch (NotEnoughMoneyException e) {
                ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
            }
        }

    }
}


/*
    before=Withdrawing...
    success.format=%d %s was withdrawn successfully
    specify.amount=Please specify integer amount for withdrawing.
    specify.not.empty.amount=Please specify valid positive integer amount for withdrawing.
    not.enough.money=Not enough money on your account, please try again
    exact.amount.not.available=Exact amount is not available
 */