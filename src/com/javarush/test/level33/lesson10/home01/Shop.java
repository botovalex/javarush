package com.javarush.test.level33.lesson10.home01;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="shop")
@XmlRootElement
public class Shop {

    public int count;
    public float profit;
}
