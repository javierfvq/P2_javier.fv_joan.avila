package Bussines;

import java.util.ArrayList;
import java.util.List;


public class InternTaskList {
    private Intern intern;
    private List<Task> tasks;


    public InternTaskList(Intern intern) {
        this.intern = intern;
        this.tasks = new ArrayList<>();

    }

    public InternTaskList(InternTaskList that) {
        this.intern = that.intern;
        this.tasks = new ArrayList<>(that.tasks);
    }

    public Intern getIntern() {
        return intern;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);

    }

}
