import main.java.MsrainyException;

import java.util.*;
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
        banner = "MS RAINY\n";
        System.out.println("Hello, I am\n"
                + banner // banner no longer generated from patorjk.com's TAAG
                + "What can I do for you?");
    }
    public static void readTaskList(List<Task> taskList) {
        if (taskList.size() == 0) { System.out.println("\tThere are no tasks"); }
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("\t" + i + ". " + taskList.get(i));
        }
    }
    public static List<Task> changeMark(List<Task> taskList, List<String> tokens, boolean mark) {
        int taskIndex = Integer.parseInt(tokens.get(1));
        Task markedTask = taskList.get(taskIndex).mark(mark);
        taskList.set(taskIndex, markedTask);
        return taskList;
    }
    public static void main(String[] args) {
        welcome();
        List<Task> tasks = new ArrayList<Task>();
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        do {
            List<String> tokens = new ArrayList<>(Arrays.asList(userInput.split(" ")));
            if (userInput.equals("list")) {
                readTaskList(tasks);
            } else if (userInput.startsWith("mark")) {
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
                    Task RemovedTask = tasks.remove(Integer.parseInt(tokens.get(1)));
                    System.out.println("\tSuccessfully removed: " + RemovedTask);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("\tThe task you requested for does not exist. Use list to see indices.");
                }
            } else {
                String taskType = tokens.remove(0);
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
                            newTask = new Deadline(String.join(" ", tokens.subList(0, byIndex)),
                                    String.join(" ", tokens.subList(byIndex + 1, tokens.size())));
                            tasks.add(newTask);
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
            System.out.println("(There are now " + tasks.size() + " tasks.)");
            userInput = scanner.nextLine();
        }
        while (!userInput.equals("bye"));
        System.out.println("\tBye. Hope to see you again soon!");
    }
}
