package com.javarush.test.level36.lesson04.big01.view;

import com.javarush.test.level36.lesson04.big01.controller.Controller;
import com.javarush.test.level36.lesson04.big01.model.ModelData;

public interface View {
    void setController(Controller controller);
    void refresh(ModelData modelData);
}
