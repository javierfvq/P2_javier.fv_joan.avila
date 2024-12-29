package Bussines;

import java.util.*;

public class Manager {
    public Map<Intern, List<Task>> assignTasksGreedy(List<Intern> interns, List<Task> tasks) {
        Map<Intern, List<Task>> assignment = new HashMap<>();
        for (Intern intern : interns) {
            assignment.put(intern, new ArrayList<>());
        }

        for (Task task : tasks) {
            Intern bestIntern = null;
            double bestTime = Double.MAX_VALUE;

            for (Intern intern : interns) {
                double time = calculateTime(intern, task);
                if (canAssignTask(intern, task, assignment) && time < bestTime) {
                    bestTime = time;
                    bestIntern = intern;
                }
            }

            if (bestIntern != null) {
                assignment.get(bestIntern).add(task);
            }
        }

        return assignment;
    }

    private boolean canAssignTask(Intern intern, Task task, Map<Intern, List<Task>> assignment) {
        List<Task> assignedTasks = assignment.get(intern);

        String building = task.getBuilding();
        for (Task assignedTask : assignedTasks) {
            if (!assignedTask.getBuilding().equals(building)) {
                return false;
            }
        }

        if (intern.isJunior()) {
            int totalDifficulty = assignedTasks.stream().mapToInt(Task::getDifficulty).sum();
            if (totalDifficulty + task.getDifficulty() > 40) {
                return false;
            }
        }

        return true;
    }

    private double calculateTime(Intern intern, Task task) {
        double skill = calculateSkill(intern, task);
        return (task.getTime() / skill) * 80;
    }

    private double calculateSkill(Intern intern, Task task) {
        double baseSkill = 0;
        double avgGrade = intern.getAvgGrade();

        if (avgGrade >= 5 && avgGrade < 7) baseSkill = avgGrade * 10;
        else if (avgGrade >= 7 && avgGrade < 9) baseSkill = avgGrade * 12;
        else if (avgGrade >= 9) baseSkill = avgGrade * 15;

        if (!intern.isJunior()) baseSkill += 10;
        if (intern.getSubject().equals(task.getName())) baseSkill += 20;

        return baseSkill;
    }
}
