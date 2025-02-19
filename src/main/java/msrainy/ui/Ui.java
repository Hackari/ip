package msrainy.ui;

/**
 * Handles user interaction by displaying messages and reading user input.
 */
public class Ui {

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
    public String showHelp() {
        return "Here is a list of commands!\n"
                + "\thelp\n"
                + "\tlist\n"
                + "\ttodo <description>\n"
                + "\tdeadline <description> /by <time>\n"
                + "\tevent <description> /from <start> /to <end>\n"
                + "\tmark <index>\n"
                + "\tunmark <index>\n"
                + "\tdelete <index>\n"
                + "\tfind <keyword\n"
                + "\tbye";
    }

    /**
     * Returns a farewell message when the application exits.
     *
     * @return The farewell message string, which is oddly morbid.
     */
    public String sayBye() {
        return "Goodbye... you won't even see this...";
    }

    /**
     * Returns an error message when loading data fails.
     *
     * @return A string indicating a loading error.
     */
    public String showLoadingError() {
        return "Oh no! Something went wrong.";
    }
}
