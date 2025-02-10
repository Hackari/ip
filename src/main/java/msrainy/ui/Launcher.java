package msrainy.ui;

import javafx.application.Application;
import msrainy.Msrainy;

/**
 * A launcher class to work around classpath issues when launching the application.
 */
public class Launcher {
    /**
     * The main entry point of the application.
     *
     * @param args Command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        Application.launch(Msrainy.class, args);
    }
}
