package Bussines.Greedy;

import Bussines.Intern;
import Bussines.Task;

import java.util.LinkedList;
import java.util.List;
import java.util.Comparator;

public class GreedyEx2 {
    public static  void greedyEx2(List<Intern> interns, List<Task> tasks) {
        List<InternHoursList> sortedInterns = new LinkedList<>();
        int TotalTime = 0;
        List <Boolean> HasBuildingAssigned = new LinkedList<>();

        for (Intern intern : interns) {
            sortedInterns.add(new InternHoursList(intern));
        }
        for (int i = 0; i < sortedInterns.size(); i++) {
            HasBuildingAssigned.add(false);
        }

        tasks.sort(Comparator.comparingInt(Task::getDifficulty).reversed());

        for (int i = 0; i < tasks.size(); i++) {
            int indexBestCandidate=-1;
            double MinorTime = Double.MAX_VALUE;
            for (int j = 0; j < sortedInterns.size(); j++) {
               if(!HasBuildingAssigned.get(j) || sortedInterns.get(j).getBuilding().equals(tasks.get(i).getBuilding())){
                    double CurrentTime =sortedInterns.get(j).getHours();
                    if (CurrentTime < MinorTime) {
                        MinorTime = CurrentTime;
                        indexBestCandidate = j;
                    }
               }
            }

            sortedInterns.get(indexBestCandidate).AddHours(CalculateTime(sortedInterns.get(indexBestCandidate).getIntern(), tasks.get(i)));
            sortedInterns.get(indexBestCandidate).addTask(tasks.get(i));
            sortedInterns.get(indexBestCandidate).setBuilding(tasks.get(i).getBuilding());
            HasBuildingAssigned.set(indexBestCandidate,true);
            TotalTime += CalculateTime(sortedInterns.get(indexBestCandidate).getIntern(), tasks.get(i));
        }
        System.out.println("Tiempo total que ha tardado : " + TotalTime + " minutos");
        System.out.println("Promedio : " + TotalTime / interns.size() + " minutos");
        System.out.println(" ");
        for (InternHoursList internHours : sortedInterns) {
            System.out.println("Becario:  " + internHours.getIntern().getName());
            System.out.println("Minutos  que ha trabajado  "+ internHours.getHours());
            System.out.println("Edificio asignado:  " + internHours.getBuilding());
            for (Task task : internHours.getTasks()) {
                System.out.println("\tTarea asignada:  " + task.getName() +"Edificio:  " + task.getBuilding());
            }
            System.out.println("------------------------------------------------------");
        }
    }

    private static  boolean matchesWithTask(Task task, Intern intern) {
        String cadena = task.getName();
        int i = cadena.indexOf(intern.getSubject());
        return i != -1;
    }

    private  static double CalculateTime(Intern intern, Task task) {

        double BonificationAVG = 0;
        double BonificationSecondYear = 0;
        double BonificationMatch = 0;
        double nota = intern.getAvgGrade();

        if (nota >= 5 && nota < 7) {
            BonificationAVG = nota * 10 * 1;
        }

        if (nota >= 7 && nota < 9) {
            BonificationAVG = nota * 10 * 1.2;
        }

        if (nota >= 9 && nota <= 10) {
            BonificationAVG = nota * 10 * 1.5;
        }

        if (!intern.isJunior()) {
            BonificationSecondYear = 10;
        }

        if (matchesWithTask(task, intern)) {
            BonificationMatch = 20;
        }
        double total_time = BonificationMatch + BonificationAVG + BonificationSecondYear;

        return ((task.getTime() / total_time) * 80);
    }
}
