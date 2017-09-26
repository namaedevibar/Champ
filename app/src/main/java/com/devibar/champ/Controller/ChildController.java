package com.devibar.champ.Controller;

import com.devibar.champ.Model.Child;
import com.devibar.champ.Model.User;

import java.util.ArrayList;

/**
 * Created by namai on 9/24/2017.
 */

public class ChildController {

    public static ArrayList<User> getChildren() {
        ArrayList<User> users = new ArrayList<>();

        users.add(new User("","daphnycabiso","Daphny","Cabiso","123456","daphny@gmail.com"));
        users.add(new User("","koberelativo","Kobe","Relativo","123456","kobe@gmail.com"));
        users.add(new User("","antonwycoco","Anton","Wycoco","123456","anton@gmail.com"));
        users.add(new User("","decereequiamco","Deceree","Quiamco","123456","deceree@gmail.com"));

        return users;
    }
}
