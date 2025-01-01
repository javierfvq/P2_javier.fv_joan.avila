package Bussines.BranchBound;
import Bussines.*;

import java.util.ArrayList;
import java.util.List;
import Bussines.BranchBound.GlobalsEx1;
import java.lang.String;


public class Ex1Config implements Comparable <Ex1Config> {
    //Lista que guardará la tarea asignada para cada becario y es lo que tiene que devolver;
    private  List<InternTaskList> InterTaskList;
    //Tiempo total necesario para completar todas las tareas
    private  double  TotalTime;
    //Lista que guarda si la tarea ha sido completada o no
    private boolean[] TaskDone;
    //Numero que guarda la posicion de la lista por la cual vamos a asignar la tarea
    private int level;

    public Ex1Config(){
        InterTaskList=new ArrayList<>();
        TotalTime=0;
        level=0;
    }
    private Ex1Config(Ex1Config ex1Config){
        InterTaskList=new ArrayList<>(ex1Config.InterTaskList);
        TotalTime=ex1Config.TotalTime;
        level=ex1Config.level;
    }
  private boolean  matchesWithTask(Task task,Intern intern ){
        String cadena=task.getName();
        int i=cadena.indexOf(intern.getSubject());
        if(i==-1){
            return false;
        }else{
            return true;
        }
  }

    public double  CalculateTime(Intern intern,Task task ){

    double BonificationAVG=0;
    double  BonificationSecondYear=0;
    double BonificationMatch=0;
    double  nota=Intern.getAvgGrade();

        if(nota>=5 && nota<7){
            BonificationAVG= nota *1;
        }

        if(nota>=7 && nota<9){
            BonificationAVG=nota *1.2;
        }

        if(nota>=9 && nota<=10) {
            BonificationAVG = nota *  1.5;
        }

        if(!Intern.isJunior()) {
            BonificationSecondYear = 10;
        }

        if((matchesWithTask(task,intern))) {
           BonificationMatch=20;
         }
        double  total_time=BonificationMatch+BonificationAVG+BonificationSecondYear;

        return (task.getTime()/total_time)*80;
    }
    public List<Ex1Config> expandir(){
        List<Ex1Config> succesors=new ArrayList<>();

        for(int i =0;i<GlobalsEx1.getNumberOfTasks();i ++){
            if(!TaskDone[i]){
                for(int j=0;j<GlobalsEx1.getNumberOfInterns();j++){
                    InternTaskList internTaskList = InterTaskList.get(j);
                    Intern intern =InterTaskList.get(j).getIntern();
                    if((intern.isJunior() &&  internTaskList.getTotalDifficulty()+GlobalsEx1.getTask(i).getDifficulty()<40) || !intern.isJunior()){
                        Ex1Config succesor = new Ex1Config(this);
                        succesor.TaskDone[i]=true;
                        succesor.InterTaskList.get(j).addTask(GlobalsEx1.getTask(i));
                        succesor.InterTaskList.get(j).AddDificulty(GlobalsEx1.getTask(i).getDifficulty());
                        succesors.add(succesor);
                        succesor.TotalTime+=CalculateTime(intern,GlobalsEx1.getTask(i));
                        succesor.level++;
                    }
                }
            }
        }
        return succesors;
    }
    public boolean esPlena(){
        return level==GlobalsEx1.getNumberOfTasks();
    }
    public double  cost(){
        return TotalTime;
    }

    private double estimacion(){
        double estimacion=0;
        for(int i =level;i<GlobalsEx1.getNumberOfTasks();i++){
            double min=Double.MAX_VALUE;
            for(int j=0;j<GlobalsEx1.getNumberOfInterns();j++){
                Intern intern = InterTaskList.get(j).getIntern();
                if((intern.isJunior() && InterTaskList.get(j).getTotalDifficulty()+GlobalsEx1.getTask(i).getDifficulty()<40) || !intern.isJunior()){
                    double time=CalculateTime(intern,GlobalsEx1.getTask(i));
                    if(time<min){
                        min=time;
                    }
                }
            }
            estimacion+=min;
        }
        return TotalTime+estimacion;
    }


    public  int compareTo(Ex1Config that ) {
        return Double.compare(this.estimacion() , that.estimacion());
    }
}