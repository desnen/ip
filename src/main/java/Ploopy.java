import java.util.*;
import java.nio.file.*;

public class Ploopy {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = "______________________________________";
        String gotIt = "Got it. I've added this task:";
        System.out.println(line + "\n" + "Hello! I'm Ploopy.\nWhat can I do for you?\n" + line);
        List<Task> arr = new ArrayList<>();
        int p = 0;
        Storage str = new Storage();
        arr = str.load();
        while (true) {
            String inp = sc.nextLine();
            int len = inp.length();
            try {
                if (inp.equals("bye")) {
                    str.save(arr);
                    System.out.println(line + "\n" + "Bye. Hope to see you again soon!\n" + line);
                    break;
                } else if (inp.equals("list")){
                    String op = line + "\n" + "Here are the tasks in your list:";
                    for (int i = 0; i < arr.size(); i++) {
                        op += "\n" + (i + 1) + "." + arr.get(i);
                    }
                    System.out.println(op + "\n" + line);
                } else if (len > 5 && inp.substring(0, 4).equals("mark") &&
                        Integer.parseInt(inp.substring(5, 6)) <= arr.size()) {
                    int rep = Integer.parseInt(inp.substring(5, 6)) - 1;
                    Task helper = arr.get(rep);
                    helper.mark(true);
                    System.out.println(line + "\n" + "Nice! I've marked this task as done:" +
                            "\n" + helper + "\n" + line);
                } else if (len > 7 && inp.substring(0, 6).equals("unmark") &&
                        Integer.parseInt(inp.substring(7, 8)) <= p + 1) {
                    int rep = Integer.parseInt(inp.substring(7, 8)) - 1;
                    Task helper = arr.get(rep);
                    helper.mark(false);
                    System.out.println(line + "\n" + "Ok, I've marked this task as not done yet:" +
                            "\n" + helper + "\n" + line);
                } else if (len > 3 && inp.substring(0, 4).equals("todo")) {
                    try {
                        if (len == 4) {
                            throw new PloopyException("OOPS!!! " +
                                    "The description of todo cannot be empty.");
                        } else {
                            String w = inp.substring(5, len);
                            Task curr = new ToDo(w);
                            arr.add(curr);
                            System.out.println(line + "\n" + gotIt + "\n" + curr + "\n" +
                                    "Now you have " + arr.size() + " tasks in the list." + "\n" + line);
                        }
                    } catch (PloopyException e) {
                        System.out.println(line + "\n" + e.getMessage() + "\n" + line);
                    }
                } else if (len > 7 && inp.substring(0, 8).equals("deadline")) {
                    try {
                        if (len == 8) {
                            throw new PloopyException("OOPS!!! " +
                                    "The time of deadline cannot be empty.");
                        } else {
                            int targ = inp.indexOf("/", 8);
                            String w = inp.substring(9, targ - 1);
                            String time = inp.substring(targ + 4, len);
                            Task curr = new Deadline(w, time);
                            arr.add(curr);
                            System.out.println(line + "\n" + gotIt + "\n" + curr + "\n" +
                                    "Now you have " + arr.size() + " tasks in the list." + "\n" + line);
                        }
                    } catch(PloopyException e) {
                        System.out.println(line + "\n" + e.getMessage() + "\n" + line);
                    }
                } else if (len > 4 && inp.substring(0, 5).equals("event")) {
                    try {
                        if (len == 5) {
                            throw new PloopyException("OOPS!!! " +
                                    "The time of event cannot be empty.");
                        } else {
                            int targ = inp.indexOf("/", 5);
                            int targ2 = inp.indexOf("/", targ + 1);
                            String w = inp.substring(6, targ - 1);
                            String time = inp.substring(targ + 6, targ2 - 1);
                            String time2 = inp.substring(targ2 + 4, len);
                            Task curr = new Event(w, time, time2);
                            arr.add(curr);
                            p += 1;
                            System.out.println(line + "\n" + gotIt + "\n" + curr + "\n" +
                                    "Now you have " + arr.size() + " tasks in the list." + "\n" + line);
                        }
                    } catch(PloopyException e) {
                        System.out.println(line + "\n" + e.getMessage() + "\n" + line);
                    }
                } else if (len > 7 && inp.substring(0, 6).equals("delete")) {
                    int ny = Integer.parseInt(inp.substring(7, len));
                    if (ny <= arr.size()) {
                        Task it = arr.get(ny - 1);
                        arr.remove(ny - 1);
                        System.out.println(line + "\n" + "Noted. I've removed this task:\n" + it + "\nNow you have "
                                        + arr.size() + " tasks in you list.\n" + line);
                    }
                } else {
                    throw new PloopyException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (PloopyException e) {
                System.out.println(line + "\n" + e.getMessage() + "\n" + line);
            }
        }
    }
}