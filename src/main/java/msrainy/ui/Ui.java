package msrainy.ui;

import java.util.Scanner;

/**
 * Handles user interaction by displaying messages and reading user input.
 */
public class Ui {
    /**
     * Returns a welcome message when the application starts.
     *
     * @return The welcome message string.
     */
    public String welcome() {
        return "Hello, I am MS RAINY\n"
                + "Please enter your commands:";
    }

    /**
     * Reads and returns a command entered by the user.
     *
     * @param scanner The Scanner object to read user input.
     * @return The user-entered command as a string.
     */
    public String readCommand(Scanner scanner) {
        return scanner.nextLine().trim();
    }

    /**
     * Returns a separator line for better readability.
     *
     * @return A string representing a separator line.
     */
    public String showLine() {
        return "===============";
    }

    /**
     * Prints an error message to the console.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.err.println(message);
    }

    /**
     * Returns a message indicating an unrecognized command.
     *
     * @return A string listing available commands.
     */
    public static String commandNotFound() {
        return "\tSorry, this command does not exist. Try\n"
                + "\tlist\n"
                + "\ttodo <description>\n"
                + "\tdeadline <description> /by <time>\n"
                + "\tevent <description> /from <start> /to <end>\n"
                + "\tmark <index>\n"
                + "\tunmark <index>\n"
                + "\tdelete <index>\n"
                + "\tbye";
    }

    /**
     * Returns a farewell message when the application exits.
     *
     * @return The farewell message string.
     */
    public String bye() {
        return "\tBye. Hope to see you again soon!";
    }

    /**
     * Returns an error message when loading data fails.
     *
     * @return A string indicating a loading error.
     */
    public String showLoadingError() {
        return "\tSomething went wrong.";
    }
}
