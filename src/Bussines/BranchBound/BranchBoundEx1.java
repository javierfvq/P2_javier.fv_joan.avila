package Bussines.BranchBound;

import Bussines.Intern;
import Bussines.InternTaskList;
import Bussines.Task;
import java.util.List;
import java.util.PriorityQueue;

public class BranchBoundEx1 {
    public static void mainBranchBoundEx1(List<Task> taskList, List<Intern> internList) {
        GlobalsEx1.GlobalInit(taskList, internList);


        //Variable que guarda el menor tiempo total necesario para completar todas las tareas
        double millor = Integer.MAX_VALUE;


        //Variable que ordena la lista segun quien tarde menos tiempo en hacer la tarea
        PriorityQueue<Ex1Config> cua = new PriorityQueue<>();

        Ex1Config primera = new Ex1Config();
        Ex1Config millorConfig = null;
        cua.offer(primera);



        while (!cua.isEmpty()) {


            Ex1Config config = cua.poll();

            List<Ex1Config> succesor = config.expandir();


            for (Ex1Config successsor : succesor) {

                if (successsor.esPlena()) {
                    if (successsor.cost() < millor) {
                        millor = successsor.cost();
                        System.out.println(successsor);

                        millorConfig = successsor;
                        System.out.printf("Nueva mejor solución: TotalTime: %.2f\n", millor);

                    }
                } else {
                    if (successsor.cost() < millor) {
                        cua.offer(successsor);
                    }
                }
            }

        }
        System.out.println("El mejor tiempo para realizar todas las tareas es : " + millor);

        List<InternTaskList> lista_mejor_config=millorConfig.getInterTaskList();
        for (InternTaskList internTaskList : lista_mejor_config) {
            System.out.println(internTaskList.getIntern().getName());
            List<Task > listTask=internTaskList.getTasks();
            for (Task task : listTask) {
                System.out.println(task.getName());

            }
        }
    }
}


