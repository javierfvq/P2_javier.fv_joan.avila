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
        double millor = Double.MAX_VALUE;


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

                        millorConfig = successsor;
                        System.out.println("Nueva mejor solución es "+ millor);

                    }
                } else {
                    if ( successsor.cost()< millor) {
                        cua.offer(successsor);
                    }
                }
            }


        }
        System.out.println(" ");
        System.out.println("El mejor tiempo para realizar todas las tareas es : " + millor);
        System.out.println(" ");

        System.out.println("Reparticion de tareas para cada becario ");
        List<InternTaskList> lista_mejor_config=millorConfig.getInterTaskList();
        List <String> lista_building=millorConfig.getBuilding();
        List <Integer> lista_total_difficulty=millorConfig.getTotalDifficulty();
        for (int i = 0; i < lista_mejor_config.size(); i++) {
            InternTaskList internTaskList = lista_mejor_config.get(i);
            String building = lista_building.get(i);
            int totalDifficulty = lista_total_difficulty.get(i);

            System.out.println("Becario: " + internTaskList.getIntern().getName());
            System.out.println("\tEdificio asignado: " + building);
            System.out.println("\tDificultad total acumulada: " + totalDifficulty);
            System.out.println("Ha hecho su primera tarea: " + millorConfig.getFirstTask().get(i));
            List<Task> listTask = internTaskList.getTasks();
            for (Task task : listTask) {
                System.out.println("\t\tTarea: " + task.getName());
            }

            System.out.println("");
        }
    }
}


