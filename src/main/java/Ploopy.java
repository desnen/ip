import java.util.*;

public class Ploopy {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = "______________________________________";
        System.out.println("Hello! I'm Ploopy.\nWhat can I do for you?\n" + line +
                "\nBye. Hope to see you again soon!\n" + line);
        System.out.println(line + "\n" + "Hello! I'm Ploopy.\nWhat can I do for you?\n" + line);
        Task[] arr = new Task[100];
        int p = 0;
        while (true) {
            String inp = sc.nextLine();
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
            } else if (inp.length() > 5 && inp.substring(0, 4).equals("mark") &&
                            Integer.parseInt(inp.substring(5, 6)) <= p + 1){
                int rep = Integer.parseInt(inp.substring(5, 6)) - 1;
                Task helper = arr[rep];
                helper.mark(true);
                System.out.println(line + "\n" + "Nice! I've marked this task as done:" +
                            "\n" + helper.msg() + "\n" + line);
            } else if (inp.length() > 7 && inp.substring(0, 6).equals("unmark") &&
                    Integer.parseInt(inp.substring(7, 8)) <= p + 1){
                int rep = Integer.parseInt(inp.substring(7, 8)) - 1;
                Task helper = arr[rep];
                helper.mark(false);
                System.out.println(line + "\n" + "Ok, I've marked this task as not done yet:" +
                        "\n" + helper.msg() + "\n" + line);
            }   else {
                arr[p] = new Task(inp);
                p += 1;
                System.out.println(line + "\n" + "added: " + inp + "\n" + line);
            }
        }
    }
}