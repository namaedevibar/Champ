package com.devibar.champ.Interface;

import com.devibar.champ.Model.Task;
import com.devibar.champ.Model.Wish;

/**
 * Created by namai on 9/27/2017.
 */

public interface OnManageTaskListener {
    void manageTask(String purpose, Task task, Wish wish);
}
