package ploopy;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import ploopy.task.Deadline;
import ploopy.task.Event;
import ploopy.task.Task;
import ploopy.task.TaskList;
import ploopy.task.ToDo;

/**
 * Stores tasks by writing data to a file
 * Loads saved tasks by reading data from the saved file
 */
public class Storage {
    private final Path filePath;

    /**
     * Constructs a Storage instance that reads from and writes to the specified file path.
     *
     * @param filePath The file path used to load and save task data.
     */
    public Storage(String filePath) {
        this.filePath = Path.of(filePath);
    }
    /**
     * Encodes a given task into a specific format
     *
     * @param t The task to be encoded.
     * @return The encoded task string
     */
    public String encode(Task t) {
        return t.encodeString();
    }

    /**
     * Returns a Task by decoding a given encoded string
     *
     * @param s The encoded string to be decoded.
     * @return The Task represented by the encoded string
     */
    public Task decode(String s) throws PloopyException {
        try {
            String[] parts = s.split("/");

            String type = parts[0];
            String isDone = parts[1];

            Task task;

            switch (type) {
            case "T":
                task = new ToDo(parts[2]);
                break;
            case "D":
                task = new Deadline(parts[2], parts[3]);
                break;
            case "E":
                task = new Event(parts[2], parts[3], parts[4]);
                break;
            default:
                throw new PloopyException("Unknown task type: " + type);
            }

            if (isDone.equals("1")) {
                task.mark(true);
            }
            return task;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new PloopyException("Corrupted task data: " + s);
        }
    }

    /**
     * Loads the saved tasks by reading data from the saved file
     *
     * @return List<Task> of saved Tasks
     * @throws PloopyException If an I/O error occurs while reading the storage file.
     */
    public List<Task> load() throws PloopyException {
        List<Task> tasks = new ArrayList<>();

        try {
            if (!Files.exists(filePath)) {
                return tasks;
            }

            List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
            for (String line : lines) {
                Task t = decode(line);
                tasks.add(t);
            }
        } catch (IOException e) {
            throw new PloopyException("Failed to load tasks: " + e.getMessage());
        }
        return tasks;
    }
    /**
     * Saves the given task list to the storage file.
     * Creates parent directories if they do not exist, and overwrites any existing file contents.
     *
     * @param tasks The task list to save.
     */
    public void save(TaskList tasks) {
        try {
            Path parent = filePath.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }

            try (BufferedWriter w = Files.newBufferedWriter(
                    filePath,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING
            )) {
                int curr = 0;
                boolean isEnd = tasks.isEnd(curr);
                Task t;
                while (!isEnd) {
                    t = tasks.give(curr);
                    w.write(encode(t));
                    w.newLine();
                    curr += 1;
                    isEnd = tasks.isEnd(curr);
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to save tasks: " + e.getMessage());
        }
    }
}
