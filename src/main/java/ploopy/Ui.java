package ploopy;

import java.util.Scanner;

import ploopy.task.Task;

/**
 * Handles all input and output for the chatbot.
 * Displays messages to the user and reads commands from standard input.
 */
public class Ui {

    /**
     * The separator line printed between chatbot interactions.
     */
    public static final String LINE = "______________________________________";

    /**
     * The acknowledgement message printed when a task is added.
     */
    public static final String GOTIT = "Got it. I've added this task:";

    private final Scanner sc;

    /**
     * Constructs a Ui instance that reads user input from standard input.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Returns the welcome message shown when the chatbot starts.
     */
    public String showWelcome() {
        return "Hello! I'm Ploopy.\nWhat can I do for you?";
    }

    /**
     * Returns the goodbye message shown when the chatbot ends.
     */
    public String showBye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns the separator line.
     */
    public String showLine() {
        return LINE;
    }

    /**
     * Returns the string indicating that a task has been successfully added.
     * Displays the added task and the updated total number of tasks.
     *
     * @param t The task that was added.
     * @param s The updated total number of tasks in the list.
     */
    public String showAddTask(Task t, int s) {
        return GOTIT + "\n" + t + "\n"
                + "Now you have " + s + " tasks in the list.";
    }

    /**
     * Returns a string indicating that a task's completion status has successfully changed.
     * Displays the task after it has been marked or unmarked.
     *
     * @param isMark True if the task is being marked as done, false if it is being unmarked.
     * @param t The task whose completion status was updated.
     */
    public String showMark(boolean isMark, Task t) {
        if (isMark) {
            return "Nice! I've marked this task as done:"
                    + "\n" + t;
        } else {
            return "Ok, I've marked this task as not done yet:"
                    + "\n" + t;
        }
    }

    /**
     * Returns the given error message to the user.
     *
     * @param s The error message to display.
     */
    public String showError(String s) {
        return s;
    }

    /**
     * Returns an error message indicating that stored tasks could not be loaded.
     */
    public String showLoadingError() {
        return "Failed to load tasks";
    }

    /**
     * Returns the next command line entered by the user.
     *
     * @return The raw command string read from standard input.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Returns a string indicating that a task has been successfully deleted.
     * Returns the deleted task and the updated total number of tasks.
     *
     * @param t The task that was deleted.
     * @param size The updated total number of tasks in the list.
     */
    public String showDelete(Task t, int size) {
        return "Noted. I've removed this task:\n" + t + "\nNow you have "
                + size + " tasks in you list.";
    }
}
