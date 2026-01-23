import java.util.Scanner;

public class Ploopy {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = "______________________________________";
        System.out.println(line + "\n" + "Hello! I'm Ploopy.\nWhat can I do for you?\n" + line);
        while (true) {
            String inp = sc.nextLine();
            if (inp.equals("bye")) {
                System.out.println(line + "\n" + "Bye. Hope to see you again soon!\n" + line);
                break;
            } else {
                System.out.println(line + "\n" + inp + "\n" + line);
            }
        }
    }
}
