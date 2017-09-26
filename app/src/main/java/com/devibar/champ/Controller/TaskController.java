package com.devibar.champ.Controller;

import com.devibar.champ.Model.Task;

import java.util.ArrayList;

/**
 * Created by namai on 9/26/2017.
 */

public class TaskController {

    public static ArrayList<Task> getTasks(){
        ArrayList<Task> tasks = new ArrayList<>();

        tasks.add(new Task("","Do Homework","Pag assignment na!","Completed"));
        tasks.add(new Task("","Clean Comfort Room","Limpyo c.r dzae","To Do"));
        tasks.add(new Task("","Hugas Plato","Panghugas plato","On-Going"));


        return tasks;
    }
}
