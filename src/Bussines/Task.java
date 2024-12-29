package Bussines;

public class Task {
    private String name;
    private String deadline;
    private int    time;
    private int    difficulty;
    private int    progress;
    private String importance;
    private String building;

    public Task(String name, String deadline, int time, int difficulty, int progress, String importance, String building) {
        this.name = name;
        this.deadline = deadline;
        this.time = time;
        this.difficulty = difficulty;
        this.progress = progress;
        this.importance = importance;
        this.building = building;
    }

    public String getName() {
        return name;
    }

    public String getDeadline() {
        return deadline;
    }

    public int getTime() {
        return time;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public int getProgress() {
        return progress;
    }

    public String getImportance() {
        return importance;
    }

    public String getBuilding() {
        return building;
    }
}