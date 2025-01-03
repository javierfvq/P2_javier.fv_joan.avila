package Bussines.Backtracking;

import Bussines.Intern;
import Bussines.Task;
import Bussines.InternTaskList;

import java.util.ArrayList;
import java.util.List;

public class BacktrackingEx2 {

    private static double minWorkloadDifference = Double.MAX_VALUE;
    private static List<InternTaskList> bestConfiguration;

    public static List<InternTaskList> backtracking(List<Intern> interns, List<Task> tasks) {
        // Initialize the best configuration
        bestConfiguration = new ArrayList<>();
        for (Intern intern : interns) {
            bestConfiguration.add(new InternTaskList(intern));
        }

        // Start recursive backtracking
        List<InternTaskList> currentConfiguration = new ArrayList<>();
        for (InternTaskList internTaskList : bestConfiguration) {
            currentConfiguration.add(new InternTaskList(internTaskList));
        }

        backtrack(currentConfiguration, tasks, 0);

        // Print the results after finding the best configuration
        System.out.println("\n=============================");
        System.out.println("Best Configuration Found");
        System.out.println("=============================");
        System.out.println("Minimum Workload Difference: " + minWorkloadDifference);
        System.out.println("Total Best Time: " + calculateTotalTime(bestConfiguration) + " minutes\n");

        for (InternTaskList internTaskList : bestConfiguration) {
            Intern intern = internTaskList.getIntern();
            List<Task> tasksAssigned = internTaskList.getTasks();

            System.out.println("Intern: " + intern.getName() + " (" + intern.getSubject() + ")");
            System.out.println("\tBuilding: " + (tasksAssigned.isEmpty() ? "No tasks assigned" : tasksAssigned.get(0).getBuilding()));
            System.out.println("\tTotal Difficulty: " + tasksAssigned.stream().mapToInt(Task::getDifficulty).sum());
            System.out.println("\tAssigned Tasks:");

            for (Task task : tasksAssigned) {
                System.out.println("\t\tTask: " + task.getName() + " (Time: " + task.getTime() + " minutes, Difficulty: " + task.getDifficulty() + ")");
            }

            System.out.println();
        }

        return bestConfiguration;
    }

    private static void backtrack(List<InternTaskList> configuration, List<Task> tasks, int level) {
        if (level == tasks.size()) {
            double workloadDifference = calculateWorkloadDifference(configuration);
            if (workloadDifference < minWorkloadDifference) {
                minWorkloadDifference = workloadDifference;
                copyConfiguration(configuration, bestConfiguration);
            }
            return;
        }

        Task task = tasks.get(level);

        for (InternTaskList internTaskList : configuration) {
            internTaskList.addTask(task);

            //System.out.println("Assigned task \"" + task.getName() + "\" to intern \"" + internTaskList.getIntern().getName() + "\".");

            if (isValidConfiguration(internTaskList, task)) {
                backtrack(configuration, tasks, level + 1);
            }

            // Undo the assignment
            internTaskList.getTasks().remove(internTaskList.getTasks().size() - 1);
        }
    }

    private static boolean isValidConfiguration(InternTaskList internTaskList, Task task) {
        List<Task> tasks = internTaskList.getTasks();

        // Check if tasks are assigned to the same building
        if (!tasks.isEmpty() && !tasks.get(0).getBuilding().equals(task.getBuilding())) {
            return false;
        }

        return true; // No additional constraints for Problem 2
    }

    private static double calculateWorkloadDifference(List<InternTaskList> configuration) {
        double maxWorkload = 0;
        double minWorkload = Double.MAX_VALUE;

        for (InternTaskList internTaskList : configuration) {
            double workload = calculateTotalWorkload(internTaskList);
            maxWorkload = Math.max(maxWorkload, workload);
            minWorkload = Math.min(minWorkload, workload);
        }

        return maxWorkload - minWorkload;
    }

    private static double calculateTotalWorkload(InternTaskList internTaskList) {
        double totalWorkload = 0;

        for (Task task : internTaskList.getTasks()) {
            double skill = calculateSkill(internTaskList.getIntern(), task);
            if (skill <= 0) skill = 1;

            totalWorkload += calculateTime(task.getTime(), skill);
        }

        return totalWorkload;
    }

    private static void copyConfiguration(List<InternTaskList> source, List<InternTaskList> destination) {
        destination.clear();
        for (InternTaskList internTaskList : source) {
            destination.add(new InternTaskList(internTaskList));
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

    private static double calculateTotalTime(List<InternTaskList> configuration) {
        double totalTime = 0;

        for (InternTaskList internTaskList : configuration) {
            for (Task task : internTaskList.getTasks()) {
                double skill = calculateSkill(internTaskList.getIntern(), task);
                if (skill <= 0) skill = 1;
                totalTime += calculateTime(task.getTime(), skill);
            }
        }

        return totalTime;
    }
}