package com.javarush.test.level34.lesson08.bonus01;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;

public class Cache<K, V> {
    private Map<K, V> cache = new WeakHashMap<>();   //TODO add your code here

    public V getByKey(K key, Class<V> clazz) throws Exception {
        //TODO add your code here

        V value = cache.get(key);

        if (value != null) {
            return value;
        } else {
            V newValue = clazz.getConstructor(key.getClass()).newInstance(key);
            cache.put(key, newValue);
            return newValue;
        }

    }

    public boolean put(V obj) {
        //TODO add your code here

        try {
            Method method = obj.getClass().getDeclaredMethod("getKey");
            method.setAccessible(true);
            K key = (K) method.invoke(obj);
            cache.put(key, obj);
            return true;
        } catch (NoSuchMethodException ignored) {
        } catch (InvocationTargetException ignored) {
        } catch (IllegalAccessException ignored) {
        }

        return false;
    }

    public int size() {
        return cache.size();
    }
}

/*
Он работает с классами(дженерик тип Т), у которых обязан быть:
а) публичный конструктор с одним параметром типа K
б) метод K getKey() с любым модификатором доступа

Задание:
1. Выберите правильный тип для поля cache. Map<K, V> cache должен хранить ключи, на которые есть активные ссылки.
Если нет активных ссылок на ключи, то они вместе со значениями должны автоматически удаляться из cache.
2. Реализуйте логику метода getByKey:
2.1. Верните объект из cache для ключа key
2.2. Если объекта не существует в кэше, то добавьте в кэш новый экземпляр используя рефлекшн, см. а)
3. Реализуйте логику метода put:
3.1. Используя рефлекшн получите ссылку на метод, описанный в п.б)
3.2. Используя рефлекшн разрешите к нему доступ
3.3. Используя рефлекшн вызовите метод getKey у объекта obj, таким образом Вы получите ключ key
3.4. Добавьте в кэш пару <key, obj>
3.5. Верните true, если метод отработал корректно, false в противном случае. Исключения игнорируйте.
 */