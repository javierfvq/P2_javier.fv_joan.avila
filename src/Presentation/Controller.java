package Presentation;

import Presentation.UI;
import Persistence.LoadFiles;
import Bussines.Intern;
import Bussines.Task;
import Bussines.Manager;
import Presentation.Enums.MenuOption;
import Bussines.*;

import Bussines.Backtracking.BacktrackingEx1;
import Bussines.Backtracking.Marking;

import java.io.IOException;
import java.util.*;

public class Controller {

    private Manager businessManager;
    private List<Intern> interns;
    private List<Task> tasks;

    public Controller() {
        this.businessManager = new Manager();
        this.interns = new ArrayList<>();
        this.tasks = new ArrayList<>();
    }

    public void run() {
        loadData();
        showMenu();
    }

    public void loadData() {
        try {
            LoadFiles loader = new LoadFiles();
            this.interns = loader.loadInterns();
            this.tasks = loader.loadTasks();
            System.out.println("Datos cargados correctamente.");
        } catch (IOException e) {
            System.err.println("Error al cargar los datos: " + e.getMessage());
        }
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            for (MenuOption menuOption : MenuOption.values()) {
                System.out.println(menuOption.getValue() + ". " + menuOption.getDescription());
            };

            option = UI.askForInteger("Seleccione una opción: ");
            MenuOption selectedOption = MenuOption.fromValue(option);

            if (selectedOption != null) {
                switch (selectedOption) {
                    case GREEDY:
                        assignTasksGreedy();
                        break;
                    case BACKTRACKING:
                        assignTasksBacktracking();
                        break;
                    case SHOW_DATA:
                        showData();
                        break;
                    case BRANCH_AND_BOUND:
                        System.out.println("Asignando tareas usando Branch and Bound en la tarea1");
                        asignTaskBranchAnbBound();
                        break;
                    case EXIT:
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } else {
                System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (option != MenuOption.EXIT.getValue());

        scanner.close();
    }

    private void assignTasksGreedy() {
        System.out.println("Asignando tareas usando Greedy...");
        Map<Intern, List<Task>> assignment = businessManager.assignTasksGreedy(interns, tasks);
    }

    private void assignTasksBacktracking() {
        System.out.println("Asignando tareas usando Backtracking...\n");

        // Crear una instancia de BacktrackingEx1
        BacktrackingEx1 backtrackingSolver = new BacktrackingEx1();

        // Crear la instancia del marcaje (estado del sistema)
        Marking marking = new Marking();

        // Crear la configuración inicial basada en las tareas y los becarios
        List<InternTaskList> configuracion = new ArrayList<>();
        for (Intern intern : interns) {
            configuracion.add(new InternTaskList(intern));
        }

        // Ejecutar el algoritmo de backtracking
        backtrackingSolver.backtracking(configuracion, 0, marking);

        // Obtener los resultados
        List<InternTaskList> resultado = backtrackingSolver.getInterTaskList();
        double tiempoTotal = backtrackingSolver.getTotalTime();

        // Imprimir el resultado
        System.out.println("Tiempo total mínimo: " + tiempoTotal);
        System.out.println("\n--- Asignación de tareas ---");
        for (InternTaskList taskList : resultado) {
            System.out.println("Becario: " + taskList.getIntern().getName());
            for (Task task : taskList.getTasks()) {
                System.out.println("  - Tarea: " + task.getName() + " (Edificio: " + task.getBuilding() + ")");
            }
        }
    }

    private void showData() {
        System.out.println("\n--- Becarios ---");
        for (Intern intern : interns) {
            System.out.println("- " + intern.getName());
        }

        System.out.println("\n--- Tareas ---");
        for (Task task : tasks) {
            System.out.println("- " + task.getName());
        }
    }
    private void asignTaskBranchAnbBound(){
        System.out.println("Asignando tareas usando Branch and Bound...");
        Bussines.BranchBound.BranchBoundEx1.mainBranchBoundEx1(tasks,interns);
    }

}