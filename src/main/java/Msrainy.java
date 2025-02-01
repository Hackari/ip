import main.java.MsrainyException;
import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Msrainy {
    public static void welcome() {
        String banner =
                "  __   __  _____   _____       _             \n"
                        + " |  \\;/  |/ ____| |  __ \\     (_)            \n"
                        + " | \\  / | (___   | |__) |__ _ _ _ __  _   _ \n"
                        + " | |\\/| |\\___ \\  |  _  // _` | | '_ \\| | | |\n"
                        + " | |  | |____) | | | \\ \\ (_| | | | | | |_| |\n"
                        + " |_|  |_|_____/  |_|  \\_\\__,_|_|_| |_|\\__, |\n"
                        + "                                       __/ |\n"
                        +       "                                      |___/ \n";
        banner = "MS RAINY";
        System.out.println("Hello, I am\n"
                + banner // banner no longer generated from patorjk.com's TAA
                );
    }
    public static void readTaskList(List<Task> taskList) {
        if (taskList.isEmpty()) { System.out.println("\tThere are no tasks"); }
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("\t" + i + ". " + taskList.get(i));
        }
    }
    public static List<Task> changeMark(List<Task> taskList, List<String> tokens, boolean mark) {
        int taskIndex = Integer.parseInt(tokens.get(0));
        Task markedTask = taskList.get(taskIndex).mark(mark);
        taskList.set(taskIndex, markedTask);
        return taskList;
    }

    public static void update(List<Task> tasks, String filename, String taskType, String index) throws IOException {
        if (taskType.equals("todo") || taskType.equals("event") || taskType.equals("deadline")) {
            FileWriter fw = new FileWriter(filename, true);
            fw.write(tasks.get(tasks.size() - 1).toData() + "\n");
            fw.close();
        } else {
            FileWriter fw = new FileWriter(filename);
            for (Task task : tasks) {
                fw.write(task.toData() + "\n");
            }
            fw.close();
        }
    }

    public static ArrayList<Task> populate(File file) throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        Scanner s = new Scanner(file);
        while (s.hasNextLine()) {
            String line = s.nextLine();
            List<String> tokens = new ArrayList<>(Arrays.asList(line.split("#")));
            String type = tokens.get(0);
            switch (type) {
            case "T":
                tasks.add(new ToDo(tokens.get(2), tokens.get(1).equals("true")));
                break;
            case "D":
                tasks.add(new Deadline(tokens.get(2), tokens.get(1).equals("true"), tokens.get(3)));
                break;
            case "E":
                tasks.add(new Event(tokens.get(2), tokens.get(1).equals("true"), tokens.get(3), tokens.get(4)));
                break;
            default:
                System.out.println("\t" + type + ": Unrecognized data entry. Will not be parsed and may be removed");
            }
        }
        return tasks;
    }

    public static void main(String[] args) {
        welcome();
        String filename = "./data/tasks.txt";
        File file = new File(filename);
        List<Task> tasks = new ArrayList<Task>();
        try {
            tasks = populate(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Please ensure it is found at: " + filename);
            System.exit(1);
        }
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        do {
            List<String> tokens = new ArrayList<>(Arrays.asList(userInput.split(" ")));
            String taskType = tokens.remove(0);
            boolean isOk = true;
            if (userInput.equals("list")) {
                readTaskList(tasks);
            } else {
                if (userInput.startsWith("mark")) {
                    try {
                        tasks = changeMark(tasks, tokens, true);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("\tThe task you requested for does not exist. Use list to see indices.");
                    } catch (NumberFormatException e) {
                        System.out.println(("\tPlease add the index of the task you want to operate on."));
                    }
                } else if (userInput.startsWith("unmark")) {
                    try {
                        tasks = changeMark(tasks, tokens, false);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("\tThe task you requested for does not exist. Use list to see indices.");
                    } catch (NumberFormatException e) {
                        System.out.println(("\tPlease add the index of the task you want to operate on."));
                    }
                } else if (userInput.startsWith("delete")) {
                    try {
                        Task RemovedTask = tasks.remove(Integer.parseInt(tokens.get(0)));
                        System.out.println("\tSuccessfully removed: " + RemovedTask);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("\tThe task you requested for does not exist. Use list to see indices.");
                    }
                } else {

                    Task newTask;
                    try {
                        switch (taskType) {
                            case "todo":
                                if (tokens.isEmpty()) {
                                    throw new MsrainyException("\tSorry, todo requires a description.");
                                }
                                newTask = new ToDo(String.join(" ", tokens));
                                tasks.add(newTask);
                                break;
                            case "deadline":
                                int byIndex = tokens.indexOf("/by");
                                if (byIndex == -1) {
                                    throw new MsrainyException("\tSorry, deadlines require a /by.");
                                }
                                if (byIndex == 0 || byIndex == tokens.size() -1) {
                                    throw new MsrainyException("\tSorry, the description and/or /by fields cannot be empty.");
                                }
                                try {
                                    newTask = new Deadline(String.join(" ", tokens.subList(0, byIndex)),
                                            String.join(" ", tokens.subList(byIndex + 1, tokens.size())));
                                    tasks.add(newTask);
                                } catch (DateTimeParseException e) {
                                    System.out.println("Please enter a datetime in the format dd/MM/yyyy HHmm");
                                }
                                break;
                            case "event":
                                int fromIndex = tokens.indexOf("/from");
                                int toIndex = tokens.indexOf("/to");
                                if (fromIndex == -1 || toIndex == -1) {
                                    throw new MsrainyException("\tSorry, events require both /from and /to.");
                                }
                                if (toIndex < fromIndex) {
                                    throw new MsrainyException("\tSorry, please write /to after /from.");
                                }
                                if (fromIndex == 0 || toIndex == tokens.size() -1 || toIndex - fromIndex == 1) {
                                    throw new MsrainyException("\tSorry, the description, /to, and/or /from fields cannot be empty.");
                                }
                                newTask = new Event(String.join(" ", tokens.subList(0, fromIndex)),
                                        String.join(" ", tokens.subList(fromIndex + 1, toIndex)),
                                        String.join(" ", tokens.subList(toIndex + 1, tokens.size())));
                                tasks.add(newTask);
                                break;
                            default:
                                isOk = false;
                                throw new MsrainyException("\tSorry, this command does not exist. Try\n" +
                                        "\tlist\n" +
                                        "\ttodo <description>\n" +
                                        "\tdeadline <description> /by <time>\n" +
                                        "\tevent <description> /from <start> /to <end>\n" +
                                        "\tmark <index>" +
                                        "\tunmark <index>" +
                                        "\tdelete <index>" +
                                        "\t bye");
                        }
                    } catch (MsrainyException e) {
                        System.out.println(e.getMessage());
                    }
                }
                if (!Thread.currentThread().isInterrupted() || !isOk) {
                    try {
                        update(tasks, filename, taskType, tokens.get(0));
                    } catch (IOException e) {
                        // Actually, this will never happen but oh well
                    } catch (IndexOutOfBoundsException e) {
                        // Ignore this too
                    }
                }
            }
            System.out.println("(There are now " + tasks.size() + " tasks.)");
            userInput = scanner.nextLine();
        }
        while (!userInput.equals("bye"));
        System.out.println("\tBye. Hope to see you again soon!");
    }
}
