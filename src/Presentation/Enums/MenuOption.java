package Presentation.Enums;

public enum MenuOption {
    GREEDY(1, "Asignar tareas (Greedy)"),
    BACKTRACKING(2, "Asignar tareas (Backtracking)"),
    BRANCH_AND_BOUND(3, "Asignar tareas (Branch and Bound)"),
    BRUTE_FORCE(4, "Asignar tareas (Brute Force)"),
    SHOW_DATA(5, "Mostrar datos cargados"),
    EXIT(6, "Salir");

    private final int value;
    private final String description;

    MenuOption(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public static MenuOption fromValue(int value) {
        for (MenuOption option : values()) {
            if (option.value == value) {
                return option;
            }
        }
        return null;
    }
}