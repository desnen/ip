package ploopy;

import ploopy.command.Command;
import ploopy.task.TaskList;

/**
 * Represents the main Ploopy chatbot application.
 * Coordinates the UI, task list, and storage components to read, parse, and execute user commands
 * until an exit command is issued.
 */
public class Ploopy {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    /**
     * Constructs a new Ploopy instance.
     * Initializes the UI and storage components, and loads previously saved tasks if available.
     * If loading fails, an empty task list is created and a loading error message is shown.
     *
     */
    public Ploopy() {
        ui = new Ui();
        storage = new Storage("data/tasks.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (PloopyException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    /**
     * Constructs a new Ploopy instance using the specified file path.
     * Initializes the UI and storage components, and loads previously saved tasks if available.
     * If loading fails, an empty task list is created and a loading error message is shown.
     *
     * @param filePath The path to the data file used for loading and saving tasks.
     */
    public Ploopy(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (PloopyException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Initialises the chatbot
     * Displays a welcome message, reads user input, parses commands, and executes them
     * until an exit command is issued.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (PloopyException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
    public static void main(String[] args) {
        new Ploopy("data/tasks.txt").run();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            assert c != null;
            String output = c.execute(tasks, ui, storage);
            return ui.showLine() + "\n" + output;
        } catch (PloopyException e) {
            return "OOPS!!! I'm sorry, but I don't know what that means :-(";
        }
    }
}
