package Bussines;

public class Intern {
    private String  name;
    private String  subject;
    private   double  avgGrade;
    private   boolean isJunior;   // Si es junior (true) o senior (false)

    public Intern(String name, String subject, double avgGrade, boolean isJunior) {
        this.name = name;
        this.subject = subject;
        this.avgGrade = avgGrade;
        this.isJunior = isJunior;
    }

    public String getName() {
        return name;
    }

    public String getSubject() {
        return subject;
    }

    public  double getAvgGrade() {
        return avgGrade;
    }

    public   boolean isJunior() {
        return isJunior;
    }
}
