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

        System.out.println("Hello, I am\n"
                + banner // banner generated from patorjk.com's TAAG
                + "What can I do for you?");
    }
    public static void readTaskList(List<Task> taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + ". " + taskList.get(i));
        }
    }
    public static List<Task> changeMark(List<Task> taskList, List<String> tokens, boolean mark) {
        int taskIndex = Integer.parseInt(tokens.get(tokens.size() - 1));
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
                tasks = changeMark(tasks, tokens, true);
            } else if (userInput.startsWith("unmark")) {
                tasks = changeMark(tasks, tokens, false);
            } else {
                String taskType = tokens.remove(0);
                Task newTask;
                switch (taskType) {
                    case "todo":
                        newTask = new ToDo(String.join(" ",tokens));
                        tasks.add(newTask);
                        break;
                    case "deadline":
                        int byIndex = tokens.indexOf("/by");
                        newTask = new Deadline(String.join(" ",tokens.subList(0, byIndex)),
                                String.join(" ",tokens.subList(byIndex + 1, tokens.size())));
                        tasks.add(newTask);
                        break;
                    case "event":
                        int fromIndex = tokens.indexOf("/from");
                        int toIndex = tokens.indexOf("/to");
                        newTask = new Event(String.join(" ",tokens.subList(0, fromIndex)),
                                String.join(" ",tokens.subList(fromIndex + 1, toIndex)),
                                String.join(" ", tokens.subList(toIndex + 1, tokens.size())));
                        tasks.add(newTask);
                        break;
                    default:
                        System.out.println("Invalid task type");
}
            }
            System.out.println("There are " + tasks.size() + " tasks.");
            userInput = scanner.nextLine();
        }
        while (!userInput.equals("bye"));
        System.out.println("Bye. Hope to see you again soon!");
    }
}
