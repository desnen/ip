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

    private Scanner sc;

    /**
     * Constructs a Ui instance that reads user input from standard input.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints the welcome message shown when the chatbot starts.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Ploopy.\nWhat can I do for you?\n" + LINE);
    }

    /**
     * Prints the goodbye message shown when the chatbot ends.
     */
    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the separator line.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Prints a message indicating that a task has been successfully added.
     * Displays the added task and the updated total number of tasks.
     *
     * @param t The task that was added.
     * @param s The updated total number of tasks in the list.
     */
    public void showAddTask(Task t, int s) {
        System.out.println(GOTIT + "\n" + t + "\n"
                + "Now you have " + s + " tasks in the list.");
    }

    /**
     * Prints a message indicating that a task's completion status has successfully changed.
     * Displays the task after it has been marked or unmarked.
     *
     * @param isMark True if the task is being marked as done, false if it is being unmarked.
     * @param t The task whose completion status was updated.
     */
    public void showMark(boolean isMark, Task t) {
        if (isMark) {
            System.out.println("Nice! I've marked this task as done:"
                    + "\n" + t);
        } else {
            System.out.println("Ok, I've marked this task as not done yet:"
                    + "\n" + t);
        }
    }

    /**
     * Prints the given error message to the user.
     *
     * @param s The error message to display.
     */
    public void showError(String s) {
        System.out.println(s);
    }

    /**
     * Prints an error message indicating that stored tasks could not be loaded.
     */
    public void showLoadingError() {
        System.out.println("Failed to load tasks");
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
     * Prints a message indicating that a task has been successfully deleted.
     * Displays the deleted task and the updated total number of tasks.
     *
     * @param index The index of the task that was deleted.
     * @param t The task that was deleted.
     * @param size The updated total number of tasks in the list.
     */
    public void showDelete(int index, Task t, int size) {
        System.out.println("Noted. I've removed this task:\n" + t + "\nNow you have "
                + size + " tasks in you list.");
    }
}
