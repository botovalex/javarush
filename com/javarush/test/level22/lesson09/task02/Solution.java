
package com.javarush.test.level22.lesson09.task02;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/* Формируем Where
Сформируйте часть запроса WHERE используя StringBuilder.
Если значение null, то параметр не должен попадать в запрос.
Пример:
{"name", "Ivanov", "country", "Ukraine", "city", "Kiev", "age", null}
Результат:
"name = 'Ivanov' and country = 'Ukraine' and city = 'Kiev'"
*/
public class Solution {


    public static StringBuilder getCondition(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        if (params == null) return sb;
        boolean isAllValuesNull = true;
        for (String s : params.values()) {
            if (s != null) {
                isAllValuesNull = false;
                break;
            }
        }
        if (isAllValuesNull) return sb;

        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (entry.getValue() != null) {
                sb.append(entry.getKey()).append(" = '").append(entry.getValue()).append("' and ");
            }
        }
        if (sb.length() != 0) {
            sb.delete(sb.length() - " and ".length(), sb.length());
        }
        return sb;
    }
}
