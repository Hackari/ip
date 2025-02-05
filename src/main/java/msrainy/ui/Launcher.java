package msrainy.ui;

import javafx.application.Application;

import msrainy.Msrainy;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Msrainy.class, args);
    }
}