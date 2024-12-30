package Bussines.BranchBound;

import Bussines.Intern;
import Bussines.Task;
import java.util.List;
import java.util.PriorityQueue;

public class BranchBoundEx1 {
    public static void mainBranchBoundEx1(List<Task> taskList, List<Intern> internList) {
        GlobalsEx1.GlobalInit(taskList, internList);
        System.out.println("Hola");

        //Variable que guarda el menor tiempo total necesario para completar todas las tareas
        double millor = Integer.MAX_VALUE;


        //Variable que ordena la lista segun quien tarde menos tiempo en hacer la tarea
        PriorityQueue<Ex1Config> cua = new PriorityQueue<>();

        Ex1Config primera = new Ex1Config();
        Ex1Config millorConfig = null;
        cua.offer(primera);

        while (!cua.isEmpty()) {
            System.out.println("HOLA2");
            Ex1Config config = cua.poll();

            List<Ex1Config> succesor = config.expandir();

            for (Ex1Config successsor : succesor) {
                if (successsor.esPlena()) {
                    if (successsor.cost() < millor) {
                        millor = successsor.cost();
                        System.out.println(successsor);
                        System.out.println("El millor fins ara");
                        millorConfig = successsor;
                    }
                } else {
                    if (successsor.cost() < millor) {
                        cua.offer(successsor);
                    }
                }
            }

        }
        System.out.println("El mejor tiempo para realizar todas las tareas es : " + millor);
    }
}


