package Bussines.Greedy;


import Bussines.Intern;
import Bussines.Task;

import java.util.LinkedList;
import java.util.List;

public class InternHoursList {
    private Intern      intern;
    private double      hours;
    private List<Task>  tasks;
    private String      building = ".";


    public InternHoursList(Intern intern) {
        this.intern = intern;
        this.hours = 0;
        this.tasks= new LinkedList<>();
    }

    public void AddHours(double hours){
        this.hours+=hours;
    }

    public Intern getIntern() {
        return intern;
    }

    public double getHours() {
        return hours;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public  void addTask(Task task) {
        tasks.add(task);
    }

    public  void setBuilding(String building){
        this.building = building;
    }

    public String getBuilding() {
        return building;
    }

}
