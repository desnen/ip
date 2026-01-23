import java.util.*;

public class Ploopy {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = "______________________________________";
        String gotIt = "Got it. I've added this task:";
        System.out.println("Hello! I'm Ploopy.\nWhat can I do for you?\n" + line +
                "\nBye. Hope to see you again soon!\n" + line);
        System.out.println(line + "\n" + "Hello! I'm Ploopy.\nWhat can I do for you?\n" + line);
        Task[] arr = new Task[100];
        int p = 0;
        while (true) {
            String inp = sc.nextLine();
            int len = inp.length();
            if (inp.equals("bye")) {
                System.out.println(line + "\n" + "Bye. Hope to see you again soon!\n" + line);
                break;
            } else if (inp.equals("list")){
                int n = 0;
                Task curr = arr[0];
                String op = line + "\n" + "Here are the tasks in your list:";
                while (curr != null) {
                    op += "\n" + (n + 1) + "." + curr.msg();
                    n += 1;
                    curr = arr[n];
                }
                System.out.println(op + "\n" + line);
            } else if (len > 5 && inp.substring(0, 4).equals("mark") &&
                            Integer.parseInt(inp.substring(5, 6)) <= p + 1){
                int rep = Integer.parseInt(inp.substring(5, 6)) - 1;
                Task helper = arr[rep];
                helper.mark(true);
                System.out.println(line + "\n" + "Nice! I've marked this task as done:" +
                            "\n" + helper.msg() + "\n" + line);
            } else if (len > 7 && inp.substring(0, 6).equals("unmark") &&
                    Integer.parseInt(inp.substring(7, 8)) <= p + 1){
                int rep = Integer.parseInt(inp.substring(7, 8)) - 1;
                Task helper = arr[rep];
                helper.mark(false);
                System.out.println(line + "\n" + "Ok, I've marked this task as not done yet:" +
                        "\n" + helper.msg() + "\n" + line);
            } else if (len > 3 && inp.substring(0, 4).equals("todo")){
                String w = inp.substring(5, len);
                arr[p] = new ToDo(w);
                p += 1;
                System.out.println(line + "\n" + gotIt + "\n" + arr[p - 1].msg() + "\n" +
                                    "Now you have " + p + " tasks in the list." + "\n" + line);
            } else if (len > 7 && inp.substring(0, 8).equals("deadline")){
                int targ = inp.indexOf("/", 8);
                String w = inp.substring(9, targ - 1);
                String time = inp.substring(targ + 4, len);
                arr[p] = new Deadline(w, time);
                p += 1;
                System.out.println(line + "\n" + gotIt + "\n" + arr[p - 1].msg() + "\n" +
                        "Now you have " + p + " tasks in the list." + "\n" + line);
            } else if (len > 4 && inp.substring(0, 5).equals("event")){
                int targ = inp.indexOf("/", 5);
                int targ2 = inp.indexOf("/", targ + 1);
                String w = inp.substring(6, targ - 1);
                String time = inp.substring(targ + 6, targ2 - 1);
                String time2 = inp.substring(targ2 + 4, len);
                arr[p] = new Event(w, time, time2);
                p += 1;
                System.out.println(line + "\n" + gotIt + "\n" + arr[p - 1].msg() + "\n" +
                        "Now you have " + p + " tasks in the list." + "\n" + line);
            } else {
                arr[p] = new Task(inp);
                p += 1;
                System.out.println(line + "\n" + "added: " + inp + "\n" + line);
            }
        }
    }
}