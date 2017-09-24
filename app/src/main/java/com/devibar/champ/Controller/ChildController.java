package com.devibar.champ.Controller;

import com.devibar.champ.Model.Child;

import java.util.ArrayList;

/**
 * Created by namai on 9/24/2017.
 */

public class ChildController {

    public static ArrayList<Child> getChildren() {
        ArrayList<Child> children = new ArrayList<>();

        children.add(new Child("Anton","Wycoco"));
        children.add(new Child("Daphny","Cabiso"));
        children.add(new Child("Deceree","Quiamco"));
        children.add(new Child("Kobe","Relativo"));


        return children;
    }
}
