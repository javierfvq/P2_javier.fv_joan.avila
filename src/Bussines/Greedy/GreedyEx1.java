package Bussines.Greedy;

import Bussines.Intern;
import Bussines.Task;
import Bussines.InternTaskList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GreedyEx1 {
    private static final int MAX_DIFFICULTY_JUNIOR = 40;

    public static void greedyEx1(List<Intern> interns, List<Task> tasks) {
        List<InternTaskList> configuration = new ArrayList<>();
        List<Integer> totalDifficulty = new ArrayList<>();

        for (Intern intern : interns) {
            configuration.add(new InternTaskList(intern));
            totalDifficulty.add(0);
        }

        tasks.sort(Comparator.comparing(Task::getDifficulty).reversed());

        double totalTime = 0;

        for (Task task : tasks) {
            InternTaskList bestOption = null;
            double minorAdjustedTime = Double.MAX_VALUE;
            int bestIndex = -1;

            for (int i = 0; i < configuration.size(); i++) {
                InternTaskList internTaskList = configuration.get(i);
                Intern intern = internTaskList.getIntern();

                if (intern.isJunior() && task.getDifficulty() + totalDifficulty.get(i) > MAX_DIFFICULTY_JUNIOR) {
                    continue;
                }

                // Calcular habilidad y tiempo ajustado
                double skill = calculateSkill(intern, task);
                if (skill <= 0) {
                    skill = 1;
                }
                double taskTime = calculateTime(task.getTime(), skill);
                double adjustedTime = taskTime + (totalDifficulty.get(i) * 10);

                if (adjustedTime < minorAdjustedTime) {
                    minorAdjustedTime = adjustedTime;
                    bestOption = internTaskList;
                    bestIndex = i;
                }
            }

            if (bestOption != null) {
                Intern intern = bestOption.getIntern();
                bestOption.addTask(task);
                totalDifficulty.set(bestIndex, totalDifficulty.get(bestIndex) + task.getDifficulty());

                double skill = calculateSkill(intern, task);
                double taskTime = calculateTime(task.getTime(), skill);
                totalTime += taskTime;
            } else {
                System.out.println("No se pudo asignar la tarea: " + task.getName());
            }
        }

        System.out.println("Tiempo total que ha tardado: " + totalTime + " minutos");
        System.out.println("Promedio: " + totalTime / interns.size() + " minutos");
        System.out.println();

        for (InternTaskList internTaskList : configuration) {
            Intern intern = internTaskList.getIntern();
            double totalTimeWorked = 0;

            for (Task task : internTaskList.getTasks()) {
                double skill = calculateSkill(intern, task);
                if (skill <= 0) skill = 1;
                totalTimeWorked += calculateTime(task.getTime(), skill);
            }

            System.out.println("Becario: " + intern.getName());
            System.out.println("Minutos que ha trabajado: " + totalTimeWorked);
            System.out.println("Edificio asignado: " + (internTaskList.getTasks().isEmpty() ? "Sin tareas asignadas" : internTaskList.getTasks().get(0).getBuilding()));

            for (Task task : internTaskList.getTasks()) {
                System.out.println("\tTarea asignada: " + task.getName() + " Edificio: " + task.getBuilding());
            }
            System.out.println("------------------------------------------------------");
        }
    }

    private static double calculateTime(int totalTime, double skill) {
        return (totalTime * 80.0) / skill;
    }

    private static double calculateSkill(Intern intern, Task task) {
        double skill = 0;

        double avgGrade = intern.getAvgGrade();
        if (avgGrade >= 5 && avgGrade < 7) {
            skill += (avgGrade - 5) * 10;
        } else if (avgGrade >= 7 && avgGrade < 9) {
            skill += 20 + (avgGrade - 7) * 12;
        } else if (avgGrade >= 9) {
            skill += 44 + (avgGrade - 9) * 15;
        }

        if (!intern.isJunior()) {
            skill += 10;
        }

        if (intern.getSubject().equals(task.getBuilding())) {
            skill += 20;
        }

        return skill;
    }
}