package msrainy.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import msrainy.TaskList;
import msrainy.command.task.Deadline;
import msrainy.command.task.Event;
import msrainy.command.task.Task;
import msrainy.command.task.ToDo;
import msrainy.ui.ParserException;

/**
 * Handles loading and saving tasks to a file for persistent storage.
 */
public class Storage {
    private final String filePath;

    /**
     * Creates a Storage instance that handles file operations.
     *
     * @param filePath The file path for storage.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified in the file path.
     *
     * @return A list of tasks retrieved from storage.
     * @throws FileNotFoundException If the file does not exist.
     */
    public List<Task> load() throws FileNotFoundException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException("File not found: " + filePath);
        }

        List<Task> tasks = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                Task task = parseTask(scanner.nextLine());
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (ParserException e) {
            // Do nothing
        }
        return tasks;
    }

    /**
     * Parses a task from a storage line.
     *
     * @param line The stored task data.
     * @return The corresponding Task object, or null if invalid.
     */
    private Task parseTask(String line) throws ParserException {
        try {
            List<String> tokens = new ArrayList<>(Arrays.asList(line.split("#")));
            if (tokens.size() < 3) {
                throw new ParserException("Invalid task format: " + line);
            }

            String type = tokens.get(0);
            boolean isDone = Boolean.parseBoolean(tokens.get(1));
            String description = tokens.get(2);

            switch (type) {
                case "T":
                    return new ToDo(description, isDone);
                case "D":
                    if (tokens.size() < 4) {
                        throw new ParserException("Invalid task format: " + line);
                    }
                    return new Deadline(description, isDone, tokens.get(3));
                case "E":
                    if (tokens.size() < 5) {
                        throw new ParserException("Invalid task format: " + line);
                    }
                    return new Event(description, isDone, tokens.get(3), tokens.get(4));
                default:
                    throw new ParserException("Invalid task format: " + line);
            }
        } catch (Exception e) {
            throw new ParserException("Invalid task format: " + line);
        }
    }

    /**
     * Appends a task to the storage file.
     *
     * @param task The task to be saved.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void update(Task task) throws IOException {
        checkFile();
        if (task == null) {
            throw new IllegalArgumentException("Task to update cannot be null");
        }
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(task.toData() + "\n");
        fw.close();
    }

    private void checkFile() {
        try {
            File file = new File(filePath);
            File directory = file.getParentFile();
            if (directory != null && !directory.exists()) {
                boolean createdDirs = directory.mkdirs();
                if (createdDirs) {
                    System.out.println("Directory created: " + directory.getAbsolutePath());
                }
            }
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            System.err.println("Error while creating file: " + filePath);
            System.exit(1);
        }
    }

    /**
     * Overwrites the storage file with the provided task list.
     *
     * @param tasks The task list to be saved.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void update(TaskList tasks) throws IOException {
        checkFile();
        if (tasks == null) {
            throw new IllegalArgumentException("TaskList cannot be null");
        }

        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < tasks.size(); i++) {
            fw.write(tasks.get(i).toData() + "\n");
        }
        fw.close();
    }
}
