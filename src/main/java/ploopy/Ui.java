package ploopy;

import java.util.Scanner;
import ploopy.task.Task;

public class Ui {

    public final static String LINE = "______________________________________";

    public final static String gotIt = "Got it. I've added this ploopy.task:";

    Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("\n" + "Hello! I'm Ploopy.\nWhat can I do for you?\n" + LINE);
    }

    public void showBye() {
        System.out.println("\n" + "Bye. Hope to see you again soon!");
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public void showAddTask(Task t, int s) {
        System.out.println("\n" + gotIt + "\n" + t + "\n" +
                "Now you have " + s + " tasks in the list.");
    }

    public void showMark(boolean isMark, Task t) {
        if (isMark) {
            System.out.println("\n" + "Nice! I've marked this ploopy.task as done:" +
                    "\n" + t);
        } else {
            System.out.println("\n" + "Ok, I've marked this ploopy.task as not done yet:" +
                    "\n" + t);
        }
    }
    public void showError(String s) {
        System.out.println(s);
    }

    public void showLoadingError() {
        System.out.println("Failed to load tasks");
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showDelete(int index, Task t, int size) {
        System.out.println("\n" + "Noted. I've removed this ploopy.task:\n" + t + "\nNow you have "
                + size + " tasks in you list.");
    }
}
