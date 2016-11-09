package com.javarush.test.level36.lesson04.big01.view;

import com.javarush.test.level36.lesson04.big01.bean.User;
import com.javarush.test.level36.lesson04.big01.controller.Controller;
import com.javarush.test.level36.lesson04.big01.model.ModelData;

import java.util.List;

public class UsersView implements View {
    private Controller controller;
    
    @Override
    public void refresh(ModelData modelData) {
        System.out.println(modelData.isDisplayDeletedUserList() ? "All deleted users:" : "All users:");
        List<User> users = modelData.getUsers();
        if (users != null) {
            for (User user : users) {
                System.out.println("\t" + user);
            }
        }
        System.out.println("===================================================");
        
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void fireEventShowAllUsers() {
        controller.onShowAllUsers();
    }

    public void fireEventShowDeletedUsers() {
        controller.onShowAllDeletedUsers();
    }

    public void fireEventOpenUserEditForm(long id) {
        controller.onOpenUserEditForm(id);
    }

}

/*

2. В UsersView создай поле-контроллер, также создай ему сеттер.

3. Реализуй логику метода refresh:
3.1. Выведи в консоль фразу "All users:".
3.2. Выведи в консоль всех юзеров, которые есть в modelData.
 Перед каждым юзером сделай отступ в виде табуляции.
3.3. В конце выведи визуальный разделитель данных
===================================================
 */