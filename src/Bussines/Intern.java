package Bussines;

public class Intern {
    private String  name;
    private String  subject;
    private static  double  avgGrade;
    private static  boolean isJunior;   // Si es junior (true) o senior (false)

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

    public static double getAvgGrade() {
        return avgGrade;
    }

    public static  boolean isJunior() {
        return isJunior;
    }
}
