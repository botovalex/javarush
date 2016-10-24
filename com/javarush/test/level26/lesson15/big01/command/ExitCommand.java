package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.ResourceBundle;

import static com.javarush.test.level26.lesson15.big01.CashMachine.RESOURCE_PATH;

/**
 * Created by barbudos on 26.08.2016.
 */
class ExitCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(RESOURCE_PATH + "exit_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("exit.question.y.n"));
        String answer = ConsoleHelper.readString();
        if (answer.equalsIgnoreCase(res.getString("yes"))) {
            ConsoleHelper.writeMessage(res.getString("thank.message"));
        }

    }
}


/*
private ResourceBundle res

exit.question.y.n=Do you really want to exit? <y,n>
yes=y
thank.message=Thank you for visiting JavaRush cash machine. Good luck.
 */