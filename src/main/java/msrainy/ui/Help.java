package main.java.msrainy.ui;

public class Help {
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
}
