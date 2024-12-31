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
    private   double  TotalTime;
    //Lista que guarda si la tarea ha sido completada o no
    private boolean[] TaskDone;
    //Numero que guarda la posicion de la lista por la cual vamos a asignar la tarea
    private  int level;

    public Ex1Config(){
        InterTaskList=GlobalsEx1.getInterTaskList();
        TaskDone=new boolean[GlobalsEx1.getNumberOfTasks()];
        TotalTime=0;
        level=0;
    }
    private Ex1Config(Ex1Config that ){
        this.InterTaskList = new ArrayList<>();
        for (InternTaskList internTaskList : that.InterTaskList) {
            this.InterTaskList.add(new InternTaskList(internTaskList));
        }
        this.TaskDone=that.TaskDone.clone();
        this.TotalTime=that.TotalTime;
        this.level=that.level;
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

    public double  CalculateTime(Intern intern,Task task){

    double BonificationAVG=0;
    double  BonificationSecondYear=0;
    double BonificationMatch=0;
    double  nota=intern.getAvgGrade();


        if(nota>=5 && nota<7){
            BonificationAVG= nota*10 *1;

        }

        if(nota>=7 && nota<9){
            BonificationAVG=nota *10*1.2;

        }

        if(nota>=9 && nota<=10) {
            BonificationAVG = nota *10*  1.5;
        }

        if(!intern.isJunior()) {
            BonificationSecondYear = 10;
        }

        if((matchesWithTask(task,intern))) {
           BonificationMatch=20;
        }
        double  total_time=BonificationMatch+BonificationAVG+BonificationSecondYear;

        return (task.getTime()/total_time)*80;
    }
    /* ANTIGUO METODO CON DOBLE BUCLE QUE NO FUNCIONABA MUY BIEN Y NO SEGUIA LA IDEA DEL ARBOL DEL BRANCH AND BOUND
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
                        succesor.TotalTime+=CalculateTime(intern,GlobalsEx1.getTask(i));
                        succesor.level++;

                    }
                }
            }
        }
        return succesors;
    }*/
    public List <Ex1Config> expandir(){
        List<Ex1Config> succesors=new ArrayList<>();
        if(level<=GlobalsEx1.getNumberOfTasks() && !TaskDone[level]){
            for(int i=0 ; i<GlobalsEx1.getNumberOfInterns();i++){
                InternTaskList internTaskList = InterTaskList.get(i);
                Intern intern =InterTaskList.get(i).getIntern();
                    if((intern.isJunior() &&  internTaskList.getTotalDifficulty()+GlobalsEx1.getTask(level).getDifficulty()<40) || !intern.isJunior()){
                        Ex1Config succesor = new Ex1Config(this);
                        succesor.TaskDone[level]=true;
                        succesor.InterTaskList.get(i).addTask(GlobalsEx1.getTask(level));
                        succesor.InterTaskList.get(i).AddDificulty(GlobalsEx1.getTask(level).getDifficulty());
                        succesor.TotalTime+=CalculateTime(intern,GlobalsEx1.getTask(level));
                        succesor.level++;
                        succesors.add(succesor);
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
    public  double getTotalTime() {
        return TotalTime;
    }
    public  int getLevel() {
        return level;
    }
    public List<InternTaskList> getInterTaskList() {
        return InterTaskList;
    }

}
