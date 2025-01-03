package Presentation;

import Bussines.Greedy.GreedyEx1;
import Bussines.Greedy.GreedyEx2;
import Bussines.Backtracking.BacktrackingEx2;
import Bussines.BranchBound.BranchBoundEx1;
import Persistence.LoadFiles;
import Bussines.Intern;
import Bussines.Task;
import Presentation.Enums.MenuOption;

import java.io.IOException;
import java.util.*;

public class Controller {

    private List<Intern> interns;
    private List<Task> tasks;

    public Controller() {
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
            System.out.println("\nDatos cargados correctamente.");
        } catch (IOException e) {
            System.err.println("Error al cargar los datos: " + e.getMessage());
        }
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Asignar tareas usando Greedy (Ejercicio 1)");
            System.out.println("2. Asignar tareas usando Greedy (Ejercicio 2)");
            System.out.println("3. Asignar tareas usando Backtracking (Ejercicio 2)");
            System.out.println("4. Asignar tareas usando Branch and Bound (Ejercicio 1)");
            System.out.println("5. Salir");
            System.out.println("=======================");

            option = UI.askForInteger("Seleccione una opción: ");
            switch (option) {
                case 1:
                    assignTasksGreedyEx1();
                    break;
                case 2:
                    assignTasksGreedyEx2();
                    break;
                case 3:
                    assignTasksBacktrackingEx2();
                    break;
                case 4:
                    asignTaskBranchAnbBound();
                    break;
                case 5:
                    System.out.println("\nSaliendo del programa...");
                    break;
                default:
                    System.out.println("\nOpción no válida. Intente de nuevo.");
            }
        } while (option != 5);

        scanner.close();
    }

    private void assignTasksGreedyEx1() {
        System.out.println("\n=== GREEDY (Ejercicio 1) ===");
        GreedyEx1.greedyEx1(interns, tasks);
    }

    private void assignTasksGreedyEx2() {
        System.out.println("\n=== GREEDY (Ejercicio 2) ===");
        GreedyEx2.greedyEx2(interns, tasks);
    }

    private void assignTasksBacktrackingEx2() {
        System.out.println("\n=== BACKTRACKING ===");
        BacktrackingEx2.backtracking(interns, tasks);
    }

    private void asignTaskBranchAnbBound() {
        System.out.println("\n=== BRANCH AND BOUND ===");
        BranchBoundEx1.mainBranchBoundEx1(tasks, interns);
    }

}