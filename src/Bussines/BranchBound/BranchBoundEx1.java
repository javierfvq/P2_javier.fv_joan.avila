package Bussines.BranchBound;

import Bussines.Intern;
import Bussines.Task;
import java.util.List;
import java.util.PriorityQueue;

public class BranchBoundEx1 {
        public static void mainBranchBoundEx1(List<Task> taskList,List <Intern> internList) {
            GlobalsEx1.GlobalInit(taskList,internList);

            //Variable que guarda el menor tiempo total necesario para completar todas las tareas
            int millor=Integer.MAX_VALUE;


            //Variable que ordena la lista segun quien tarde menos tiempo en hacer la tarea
            PriorityQueue<Ex1Config> cua =new PriorityQueue<>();









            }

}
