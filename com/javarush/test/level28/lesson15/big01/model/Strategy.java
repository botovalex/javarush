package com.javarush.test.level28.lesson15.big01.model;

import com.javarush.test.level28.lesson15.big01.vo.Vacancy;

import java.io.IOException;
import java.util.List;

/**
 * Created by barbudos on 09.09.2016.
 */
public interface Strategy {
    List<Vacancy> getVacancies(String searchString) throws IOException;
}
