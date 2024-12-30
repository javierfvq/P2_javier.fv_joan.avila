package Persistence;

import Bussines.Intern;
import Bussines.Task;
import Persistence.PersistenceExceptions.PersistenceException;

import  java.io.*;
import  java.nio.file.*;
import  java.util.*;

public class LoadFiles {
    private static final String FILE_PATH_INTERNS = "src/files/PAED-2425-P2-Datasets/interns.paed";
    private static final String FILE_PATH_TASKS   = "src/files/PAED-2425-P2-Datasets/tasks.paed";

    private static final int ATRIBUTES_PER_INTERN = 4;
    private static final int ATRIBUTES_PER_TASK   = 7;

    public List<Intern> loadInterns() throws IOException {
        List<Intern> interns = new ArrayList<>();
        List<String> totalLines = Files.readAllLines(Paths.get(FILE_PATH_INTERNS));

        try {
            // Ignoro la primera linea del fichero
            for (int i = 1; i < totalLines.size(); i++) {
                String line = totalLines.get(i);
                try {
                    String[] details = line.split(";");

                    if (details.length != ATRIBUTES_PER_INTERN) {
                        throw new PersistenceException("La linea " + line + " no sigue el formato adecuado");
                    }

                    String name = details[0];
                    String subject = details[1];
                    double avgGrade = Double.parseDouble(details[2]);
                    boolean isJunior = Boolean.parseBoolean(details[3]);
                    interns.add(new Intern(name, subject, avgGrade, isJunior));

                } catch (NumberFormatException e) {
                    throw new PersistenceException("Error al convertir datos numéricos en la línea: " + line, e);
                }
            }
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al leer el archivo: " + FILE_PATH_INTERNS, e);
        }

        return interns;
    }

    public List<Task> loadTasks() throws IOException {
        List<Task> tasks = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get(FILE_PATH_TASKS));

        try {
            // Ignoro la primera linea del fichero
            for (int i = 1; i < lines.size(); i++) {
                String line = lines.get(i);
                try {
                    String[] details = line.split(";");
                    if (details.length != ATRIBUTES_PER_TASK) {
                        throw new PersistenceException("La linea " + line + " no sigue el formato adecuado");
                    }

                    String name = details[0];
                    String deadline = details[1];
                    int time = Integer.parseInt(details[2]);
                    int difficulty = Integer.parseInt(details[3]);
                    int progress = Integer.parseInt(details[4]);
                    String importance = details[5];
                    String building = details[6];
                    tasks.add(new Task(name, deadline, time, difficulty, progress, importance, building));

                } catch (NumberFormatException e) {
                    throw new PersistenceException("Error al convertir datos numéricos en la línea: " + line, e);
                }
            }
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al leer el archivo: " + FILE_PATH_TASKS, e);
        }

        return tasks;
    }
}