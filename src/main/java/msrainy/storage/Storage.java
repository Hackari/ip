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

/**
 * Handles loading and saving tasks to a file for persistent storage.
 */
public class Storage {
    private final String filePath;

    public Storage(String filepath) {
        this.filePath = filepath;
    }

    /**
     * Loads tasks from the file specified in the file path.
     *
     * @return A list of tasks retrieved from storage.
     * @throws FileNotFoundException If the file does not exist.
     */
    public List<Task> load() throws FileNotFoundException {
        File file = new File(filePath);
        ArrayList<Task> tasks = new ArrayList<>();
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

    /**
     * Appends a task to the storage file.
     *
     * @param task The task to be saved.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void update(Task task) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(task.toData() + "\n");
        fw.close();
    }

    /**
     * Overwrites the storage file with the provided task list.
     *
     * @param tasks The task list to be saved.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void update(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < tasks.size(); i++) {
            fw.write(tasks.get(i).toData() + "\n");
        }
        fw.close();
    }
}
