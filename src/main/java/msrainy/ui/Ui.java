package msrainy.ui;

import java.util.Scanner;

/**
 * Handles user interaction by displaying messages and reading user input.
 */
public class Ui {
    public Ui() {
        welcome();
    }

    /**
     * Displays a welcome message when the application starts.
     */
    public void welcome() {
        System.out.println("Hello, I am MS RAINY\n"
                + "Please enter your commands:");
    }

    /**
     * Reads and returns a command entered by the user.
     *
     * @param scanner The Scanner object to read user input.
     * @return The user-entered command as a string.
     */
    public String readCommand(Scanner scanner) {
        return scanner.nextLine();
    }

    /**
     * Displays a separator line for better readability.
     */
    public void showLine() {
        System.out.println("===============");
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Displays a general message.
     *
     * @param message The message to be displayed.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Displays a message when an unrecognized command is entered.
     */
    public static void commandNotFound() {
        System.out.println("\tSorry, this command does not exist. Try\n"
                + "\tlist\n"
                + "\ttodo <description>\n"
                + "\tdeadline <description> /by <time>\n"
                + "\tevent <description> /from <start> /to <end>\n"
                + "\tmark <index>"
                + "\tunmark <index>"
                + "\tdelete <index>"
                + "\t bye");
    }

    /**
     * Displays a farewell message when the application exits.
     */
    public void bye() {
        System.out.println("\tBye. Hope to see you again soon!");
    }

    /**
     * Displays an error message when loading data fails.
     */
    public void showLoadingError() {
        System.out.println("\tSomething went wrong.");
    }
}