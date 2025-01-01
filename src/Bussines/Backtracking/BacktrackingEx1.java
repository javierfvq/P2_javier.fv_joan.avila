package Bussines.Backtracking;

import Bussines.Intern;
import Bussines.Task;
import Bussines.InternTaskList;

import java.util.ArrayList;
import java.util.List;

public class BacktrackingEx1 {

    // Lista que guardará la tarea asignada para cada becario y es lo que tiene que devolver
    private List<InternTaskList> InterTaskList;

    // Tiempo total necesario para completar todas las tareas
    private double TotalTime;

    public BacktrackingEx1() {
        this.TotalTime = Double.MAX_VALUE;
        this.InterTaskList = new ArrayList<>();
    }

    public void backtracking(List<InternTaskList> configuracion, int nivel, Marking marking) {
        // Caso base: Si se han procesado todos los niveles (todas las tareas asignadas)
        if (nivel == configuracion.size()) {
            double currentTotalTime = calculateTotalTime();

            System.out.println("currentTotalTime: " + currentTotalTime + ", TotalTime: " + TotalTime);

            // Poda basada en la mejor solución:
            // Si el tiempo total actual es menor que el mejor tiempo registrado,
            // actualizamos la mejor solución encontrada.
            if (currentTotalTime < TotalTime) {
                TotalTime = currentTotalTime;
                InterTaskList = cloneCurrentConfiguration(configuracion);
            }

            return;
        }

        for (InternTaskList taskList : configuracion) {
            Intern currentIntern = taskList.getIntern();

            if (taskList.getTasks().isEmpty() || nivel >= taskList.getTasks().size()) {
                continue; // Pasar al siguiente becario si no hay tareas o el nivel está fuera de rango
            }

            Task currentTask = taskList.getTasks().get(nivel);

            // Marcaje:
            // Se asigna la tarea actual al becario actual y se actualiza el estado del sistema.
            marking.assign(currentIntern, currentTask);

            // Validación del estado (Poda basada en restricciones):
            // Si la configuración actual sigue siendo válida según las restricciones,
            // se continúa explorando la siguiente tarea.
            if (marking.isViable()) {
                backtracking(configuracion, nivel + 1, marking);
            }

            marking.unassign(currentIntern, currentTask);
        }
    }

    private double calculateTotalTime() {
        double totalTime = 0;
        for (InternTaskList taskList : InterTaskList) {
            for (Task task : taskList.getTasks()) {
                totalTime += task.getTime();
            }
        }
        return totalTime;
    }

    public double getTotalTime() {
        return TotalTime;
    }

    private List<InternTaskList> cloneCurrentConfiguration(List<InternTaskList> configuracion) {
        List<InternTaskList> clonedList = new ArrayList<>();
        for (InternTaskList taskList : configuracion) {
            InternTaskList newTaskList = new InternTaskList(taskList.getIntern());
            newTaskList.getTasks().addAll(taskList.getTasks());
            clonedList.add(newTaskList);
        }
        return clonedList;
    }

    public List<InternTaskList> getInterTaskList() {
        return InterTaskList;
    }
}