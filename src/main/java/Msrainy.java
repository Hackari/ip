import java.util.Scanner;
public class Msrainy {
    public static void main(String[] args) {
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
                + "What can I do for you?\n");
        String userInput = "";
        Scanner scanner = new Scanner(System.in);
        while (!userInput.equals("bye")) {
            userInput = scanner.nextLine();
            System.out.println(userInput);
        }
        System.out.println("Bye. Hope to see you again soon!\n");
    }
}
