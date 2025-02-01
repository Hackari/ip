package msrainy.ui;

import java.util.Scanner;

public class Ui {
    public Ui() {
        welcome();
    }

    public void welcome() {
        System.out.println("Hello, I am MS RAINY\n"
                + "Please enter your commands:");
    }

    public String readCommand(Scanner scanner) {
        return scanner.nextLine();
    }

    public void showLine() {
        System.out.println("===============");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public static void commandNotFound() {
        System.out.println("\tSorry, this command does not exist. Try\n" +
                "\tlist\n" +
                "\ttodo <description>\n" +
                "\tdeadline <description> /by <time>\n" +
                "\tevent <description> /from <start> /to <end>\n" +
                "\tmark <index>" +
                "\tunmark <index>" +
                "\tdelete <index>" +
                "\t bye");
    }

    public void bye() {
        System.out.println("\tBye. Hope to see you again soon!");
    }

    public void showLoadingError() {
        System.out.println("\tSomething went wrong.");
    }
}

