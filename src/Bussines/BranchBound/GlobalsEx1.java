package Bussines.BranchBound;
import Bussines.Intern;
import Bussines.InternTaskList;
import Bussines.Task;

import java.util.ArrayList;
import java.util.List;


import Bussines.Task;

public class GlobalsEx1 {
    private static  int NumberOfTasks=0;
    private static  int numberOfInterns=0;
    private static  List <InternTaskList> InterTaskList= new ArrayList<>();
    private static List <Task> TaskList = new ArrayList<>();
    private static List <Intern> InternList= new ArrayList<>();

    public static void  GlobalInit(List<Task> listTask, List<Intern> internList){
        NumberOfTasks = listTask.size();
        numberOfInterns = internList.size();

        for (Intern intern : internList) {
            InterTaskList.add(new InternTaskList(intern));
        }
        for (Task task : listTask) {
            TaskList.add(task);
        }
        for (Intern intern : internList) {
            InternList.add(intern);
        }
    }

    public static int getNumberOfInterns() {
        return numberOfInterns;
    }

    public static int getNumberOfTasks() {
        return NumberOfTasks;
    }

    public static Task getTask(int i){
       return TaskList.get(i);
    }
  
    public static List<Intern> getInternList() {
        return InternList;
    }
    public static List<Task> getTaskList() {
        return TaskList;
    }
    public static List<InternTaskList> getInterTaskList() {
        return InterTaskList;
    }

}