package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.Operation;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by barbudos on 26.08.2016.
 */
public final class CommandExecutor {
    private static Map<Operation, Command> commandMap = new HashMap<>();

    static {
        commandMap.put(Operation.LOGIN, new LoginCommand());
        commandMap.put(Operation.INFO, new InfoCommand());
        commandMap.put(Operation.DEPOSIT, new DepositCommand());
        commandMap.put(Operation.WITHDRAW, new WithdrawCommand());
        commandMap.put(Operation.EXIT, new ExitCommand());
    }

    private CommandExecutor() {}

    public static final void execute(Operation operation) throws InterruptOperationException {
        commandMap.get(operation).execute();
    }
}


/*

4. Создадим public класс CommandExecutor, через который можно будет взаимодействовать со всеми командами.
Создадим ему статическую карту Map<Operation, Command>, которую проинициализируем всеми известными нам операциями и командами.

4.1 Создадим метод public static final void execute(Operation operation), который будет дергать метод execute у нужной команды.
Реализуйте эту логику.
4.2. Расставьте правильно модификаторы доступа учитывая, что единственная точка входа - это метод execute.

    INFO,
    DEPOSIT,
    WITHDRAW,
    EXIT;
 */