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
    public static void readList(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + ". " + list.get(i));
        }
    }
    public static void main(String[] args) {
        welcome();
        List<String> tasks = new ArrayList<String>();
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        do {
            if (userInput.equals("list")) {
                readList(tasks);
            } else {
                tasks.add(userInput);
                System.out.println("added: " + userInput);
            }
            userInput = scanner.nextLine();
        }
        while (!userInput.equals("bye"));
        System.out.println("Bye. Hope to see you again soon!");
    }
}
