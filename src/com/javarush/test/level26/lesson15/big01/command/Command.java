package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

/**
 * Created by barbudos on 26.08.2016.
 */
interface Command {
    void execute() throws InterruptOperationException;
}


