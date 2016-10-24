package com.javarush.test.level28.lesson15.big01;

import com.javarush.test.level28.lesson15.big01.model.HHStrategy;
import com.javarush.test.level28.lesson15.big01.model.Model;
import com.javarush.test.level28.lesson15.big01.model.MoikrugStrategy;
import com.javarush.test.level28.lesson15.big01.model.Provider;
import com.javarush.test.level28.lesson15.big01.view.HtmlView;

import java.io.IOException;

/**
 * Created by barbudos on 09.09.2016.
 */
public class Aggregator {
    public static void main(String[] args) throws IOException {
        HtmlView htmlView = new HtmlView();
        Provider hhProvider = new Provider(new HHStrategy());
        Provider mkProvider = new Provider(new MoikrugStrategy());
        Model model = new Model(htmlView, hhProvider, mkProvider);
        Controller controller = new Controller(model);
        htmlView.setController(controller);
        htmlView.userCitySelectEmulationMethod();
    }
}

/*
2. В методе main создай провайдер для MoikrugStrategy. Передай этот провайдер в конструктор Model.
Это удобно сделать, т.к. модель принимает много провайдеров.
Остальную логику менять не нужно. Видишь, как легко расширять функционал?
От правильной архитектуры зависит многое.
 */
