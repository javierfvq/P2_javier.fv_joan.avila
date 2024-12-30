package Bussines.BranchBound;
import Bussines.Intern;
import Bussines.InternTaskList;
import Bussines.Task;
import java.util.List;


import Bussines.Task;

public class GlobalsEx1 {
    private static  int NumberOfTasks=0;
    private static  int numberOfInterns=0;
    private static  List <InternTaskList> InterTaskList;
    private static List <Task> TaskList;
    private static List <Intern> InternList;

    public static void  GlobalInit(List<Task> listTask, List<Intern> internList){
        GlobalsEx1 globalsEx1 = new GlobalsEx1();
        globalsEx1.NumberOfTasks = listTask.size();
        globalsEx1.numberOfInterns = internList.size();
        for (Intern intern : internList) {
            globalsEx1.InterTaskList.add(new InternTaskList(intern));
        }
        for (Task task : listTask) {
            globalsEx1.TaskList.add(task);
        }
        for (Intern intern : internList) {
            globalsEx1.InternList.add(intern);
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
}

