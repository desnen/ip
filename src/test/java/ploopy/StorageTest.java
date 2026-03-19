package ploopy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import ploopy.task.Task;
import ploopy.task.ToDo;

public class StorageTest {

    @TempDir
    Path tempDir;

    private Storage storageAt(String relativePath) {
        return new Storage(tempDir.resolve(relativePath).toString());
    }

    @Test
    public void encodeTodo() {
        Storage storage = storageAt("tasks.txt");
        Task task = new ToDo("read book");

        assertEquals(task.encodeString(), storage.encode(task));
    }

    @Test
    public void decodeTodo() throws PloopyException {
        Storage storage = storageAt("tasks.txt");

        Task decoded = storage.decode("T/0/read book");

        assertEquals("T/0/read book", storage.encode(decoded));
    }

    @Test
    public void decodeDeadline() throws PloopyException {
        Storage storage = storageAt("tasks.txt");

        Task decoded = storage.decode("D/1/submit report/2026-03-19");

        assertEquals("D/1/submit report/2026-03-19", storage.encode(decoded));
    }

    @Test
    public void decodeEvent() throws PloopyException {
        Storage storage = storageAt("tasks.txt");

        Task decoded = storage.decode("E/1/project meeting/2026-03-20 1400/2026-03-20 1600");

        assertEquals(
                "E/1/project meeting/2026-03-20 1400/2026-03-20 1600",
                storage.encode(decoded)
        );
    }

    @Test
    public void decode_unknownTaskType_throwsException() {
        Storage storage = storageAt("tasks.txt");

        PloopyException ex = assertThrows(PloopyException.class, () ->
                storage.decode("X/0/something"));

        assertTrue(ex.getMessage().contains("Unknown task type"));
    }

    @Test
    public void decode_corruptedData_throwsException() {
        Storage storage = storageAt("tasks.txt");

        PloopyException ex = assertThrows(PloopyException.class, () ->
                storage.decode("D/0/onlyDescription"));

        assertTrue(ex.getMessage().contains("Corrupted task data"));
    }

    @Test
    public void load_missingFile_returnsEmptyList() throws PloopyException {
        Storage storage = storageAt("does-not-exist.txt");

        List<Task> tasks = storage.load();

        assertTrue(tasks.isEmpty());
    }

    @Test
    public void loadValidFile() throws Exception {
        Path file = tempDir.resolve("tasks.txt");
        Files.write(file, List.of(
                "T/0/read book",
                "D/1/submit report/2026-03-19",
                "E/0/team sync/2026-03-20 1000/2026-03-20 1100"
        ), StandardCharsets.UTF_8);

        Storage storage = new Storage(file.toString());
        List<Task> tasks = storage.load();

        List<String> encoded = tasks.stream()
                .map(storage::encode)
                .collect(Collectors.toList());

        assertEquals(List.of(
                "T/0/read book",
                "D/1/submit report/2026-03-19",
                "E/0/team sync/2026-03-20 1000/2026-03-20 1100"
        ), encoded);
    }

    @Test
    public void loadWrongPathIsDirectory() throws IOException {
        Path dir = tempDir.resolve("notAFile");
        Files.createDirectory(dir);

        Storage storage = new Storage(dir.toString());

        PloopyException ex = assertThrows(PloopyException.class, storage::load);
        assertTrue(ex.getMessage().contains("Failed to load tasks"));
    }
}
