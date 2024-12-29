package Presentation;

import java.util.Scanner;

public class UI {
    public static int askForInteger(String prompt) {
        Scanner scanner = new Scanner(System.in);
        int number;

        while (true) {
            try {
                System.out.print(prompt);
                number = Integer.parseInt(scanner.nextLine());
                return number;
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor, introduzca un número entero.");
            }
        }
    }
}
