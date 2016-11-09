package com.javarush.test.level32.lesson08.home01;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by barbudos on 08.10.2016.
 */
public class CustomInvocationHandler implements InvocationHandler {
    private SomeInterfaceWithMethods origianl;

    public CustomInvocationHandler(SomeInterfaceWithMethods origianl) {
        this.origianl = origianl;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println(method.getName() + " in");
        method.invoke(origianl, args);
        System.out.println(method.getName() + " out");
        return null;
    }
}

/*
3) Перед вызовом любого метода у оригинального объекта должна выводиться фраза [methodName in].
4) После вызова любого метода у оригинального объекта должна выводиться фраза [methodName out].
5) Реализовать логику метода getProxy, который должен создавать прокси для интерфейса SomeInterfaceWithMethods.
 */