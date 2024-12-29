package Presentation;

import Presentation.UI;
import Persistence.LoadFiles;
import Bussines.Intern;
import Bussines.Task;
import Bussines.Manager;
import Presentation.Enums.MenuOption;

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
        System.out.println("Asignando tareas usando Backtracking (no implementado aún).\n");
        // Aquí iría la implementación del backtracking
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

}