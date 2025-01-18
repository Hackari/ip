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
    public static void readTaskList(List<Task> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + ". " + list.get(i));
        }
    }
    public static List<Task> changeMark(List<Task> list, String userInput, boolean mark) {
        String[] tokens = userInput.split(" ");
        int taskIndex = Integer.parseInt(tokens[tokens.length - 1]);
        Task markedTask = list.get(taskIndex).mark(mark);
        list.set(taskIndex, markedTask);
        return list;
    }
    public static void main(String[] args) {
        welcome();
        List<Task> tasks = new ArrayList<Task>();
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        do {
            if (userInput.equals("list")) {
                readTaskList(tasks);
            } else if (userInput.startsWith("mark")) {
                tasks = changeMark(tasks, userInput, true);
            } else if (userInput.startsWith("unmark")) {
                tasks = changeMark(tasks, userInput, false);
            } else {
                Task newTask = new Task(userInput);
                tasks.add(newTask);
                System.out.println("added: " + userInput);
            }
            userInput = scanner.nextLine();
        }
        while (!userInput.equals("bye"));
        System.out.println("Bye. Hope to see you again soon!");
    }
}
