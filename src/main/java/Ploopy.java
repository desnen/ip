import java.util.*;

public class Ploopy {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = "______________________________________";
        System.out.println("Hello! I'm Ploopy.\nWhat can I do for you?\n" + line +
                "\nBye. Hope to see you again soon!\n" + line);
        System.out.println(line + "\n" + "Hello! I'm Ploopy.\nWhat can I do for you?\n" + line);
        String[] arr = new String[100];
        int p = 0;
        while (true) {
            String inp = sc.nextLine();
            if (inp.equals("bye")) {
                System.out.println(line + "\n" + "Bye. Hope to see you again soon!\n" + line);
                break;
            } else if (inp.equals("list")){
                int n = 0;
                String curr = arr[0];
                String op = line;
                while (curr != null) {
                    op += "\n" + (n + 1) + ". " + curr;
                    n += 1;
                    curr = arr[n];
                }
                System.out.println(op + "\n" + line);
            } else {
                arr[p] = inp;
                p += 1;
                System.out.println(line + "\n" + "added: " + inp + "\n" + line);
            }
        }
    }
}