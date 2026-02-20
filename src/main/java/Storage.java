import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final Path filePath;

    public Storage(String filePath) {
        this.filePath = Path.of(filePath);
    }

    public String encode(Task t) {
        return t.encodeString();
    }

    public Task decode(String s) throws PloopyException{
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
                return null;
        }

        if (isDone.equals("1")) {
            task.mark(true);
        }
        return task;
    }

    public List<Task> load() throws PloopyException{
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

    public void save(TaskList tasks) {
        try {
            Path parent = filePath.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }

            try(BufferedWriter w = Files.newBufferedWriter(
                    filePath,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING
            )) {
                int curr = 0;
                boolean isEnd = tasks.isEnd(curr);
                Task t;
                while(!isEnd) {
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
