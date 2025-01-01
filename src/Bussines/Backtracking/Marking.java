package Bussines.Backtracking;

import Bussines.Intern;
import Bussines.Task;

import java.util.HashMap;
import java.util.Map;

public class Marking {
    // Dificultad acumulada por becario
    private Map<Intern, Integer> difficultyMap;

    // Tiempo total acumulado
    private double totalTime;

    public Marking() {
        this.difficultyMap = new HashMap<>();
        this.totalTime = 0;
    }

    public void assign(Intern intern, Task task) {
        difficultyMap.put(intern, difficultyMap.getOrDefault(intern, 0) + task.getDifficulty());
        totalTime += task.getTime();
    }

    public void unassign(Intern intern, Task task) {
        difficultyMap.put(intern, difficultyMap.get(intern) - task.getDifficulty());
        totalTime -= task.getTime();
    }

    // Comprueba si la configuración actual es válida
    public boolean isViable() {
        for (Map.Entry<Intern, Integer> entry : difficultyMap.entrySet()) {
            Intern intern = entry.getKey();
            int totalDifficulty = entry.getValue();
            if (intern.isJunior() && totalDifficulty > 40) {
                return false;
            }
        }
        return true;
    }

    // Devuelve el tiempo total actual
    public double getTotalTime() {
        return totalTime;
    }
}