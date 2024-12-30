package Bussines;

import java.util.ArrayList;
import java.util.List;

public class InternTaskList {
    private Intern intern;
    private List<Task> tasks;

    private int TotalDifficulty;


    public InternTaskList(Intern intern) {
        this.intern = intern;
        this.tasks = new ArrayList<>();
        this.TotalDifficulty=0;
    }
    public InternTaskList(InternTaskList that) {
        this.intern = that.intern;
        this.tasks = new ArrayList<>(that.tasks);
        this.TotalDifficulty = that.TotalDifficulty;
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


    public void AddDificulty(int dificulty){
        TotalDifficulty+=dificulty;
    }
    public int getTotalDifficulty() {
        return TotalDifficulty;
    }

}
