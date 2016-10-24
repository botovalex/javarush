package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.ResourceBundle;

import static com.javarush.test.level26.lesson15.big01.CashMachine.RESOURCE_PATH;

/**
 * Created by barbudos on 30.08.2016.
 */
class LoginCommand implements Command {
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(RESOURCE_PATH + "verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(RESOURCE_PATH + "login_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        while (true){
            ConsoleHelper.writeMessage(res.getString("specify.data"));
            String card = ConsoleHelper.readString();
            String pin = ConsoleHelper.readString();
            if (!card.matches("^\\s*\\d{12}\\s*$") || !pin.matches("^\\s*\\d{4}\\s*$")) {
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                continue;
            }
            if (!validCreditCards.containsKey(card.trim()) || !validCreditCards.getString(card.trim()).equals(pin.trim())) {
                ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), card));
                ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                continue;
            } else {
                ConsoleHelper.writeMessage(String.format(res.getString("success.format"), card));
                break;
            }
        }
    }
}


/*
    before=Logging in...
    specify.data=Please specify your credit card number and pin code or type 'EXIT' for exiting.
    success.format=Credit card [%s] is verified successfully!
    not.verified.format=Credit card [%s] is not verified.
    try.again.or.exit=Please try again or type 'EXIT' for urgent exiting
    try.again.with.details=Please specify valid credit card number - 12 digits, pin code - 4 digits.
 */