package ploopy;

import ploopy.command.AddCommand;
import ploopy.command.Command;
import ploopy.command.DeleteCommand;
import ploopy.command.ExitCommand;
import ploopy.command.ListCommand;
import ploopy.command.MarkCommand;
import ploopy.task.Deadline;
import ploopy.task.Event;
import ploopy.task.Task;
import ploopy.task.ToDo;

public class Parser {

    public Parser() {
    }

    public static Command parse(String fullCommand) throws PloopyException {

        int len = fullCommand.length();

        if (fullCommand.equals("bye")) {
            return new ExitCommand();
        } else if (fullCommand.equals("list")) {
            return new ListCommand();
        } else if (fullCommand.startsWith("mark")) {
            if (len == 4) {
                throw new PloopyException("OOPS!!! "
                        + "The index of mark cannot be empty.");
            } else {
                try {
                    int rep = Integer.parseInt(fullCommand.substring(5)) - 1;
                    return new MarkCommand(true, rep);
                } catch (NumberFormatException e) {
                    throw new PloopyException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
        } else if (fullCommand.startsWith("unmark")) {
            if (len == 6) {
                throw new PloopyException("OOPS!!! "
                        + "The index of unmark cannot be empty.");
            } else {
                try {
                    int rep = Integer.parseInt(fullCommand.substring(7)) - 1;
                    return new MarkCommand(false, rep);
                } catch (NumberFormatException e) {
                    throw new PloopyException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
        } else if (fullCommand.startsWith("todo")) {
            if (len == 4) {
                throw new PloopyException("OOPS!!! "
                        + "The description of todo cannot be empty.");
            } else {
                String w = fullCommand.substring(5, len);
                if (w.isBlank()) {
                    throw new PloopyException("OOPS!!! "
                            + "The description of todo cannot be empty.");
                } else {
                    Task curr = new ToDo(w);
                    return new AddCommand(curr);
                }
            }
        } else if (fullCommand.startsWith("deadline")) {
            if (len == 8) {
                throw new PloopyException("OOPS!!! "
                        + "The time of deadline cannot be empty.");
            } else {
                try {
                    int targ = fullCommand.indexOf("/", 8);
                    String w = fullCommand.substring(9, targ - 1);
                    String time = fullCommand.substring(targ + 4, len);
                    Task curr = new Deadline(w, time);
                    return new AddCommand(curr);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new PloopyException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
        } else if (fullCommand.startsWith("event")) {
            if (len == 5) {
                throw new PloopyException("OOPS!!! "
                        + "The time of event cannot be empty.");
            } else {
                try {
                    int targ = fullCommand.indexOf("/", 5);
                    int targ2 = fullCommand.indexOf("/", targ + 1);
                    String w = fullCommand.substring(6, targ - 1);
                    String time = fullCommand.substring(targ + 6, targ2 - 1);
                    String time2 = fullCommand.substring(targ2 + 4, len);
                    Task curr = new Event(w, time, time2);
                    return new AddCommand(curr);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new PloopyException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
        } else if (fullCommand.startsWith("delete")) {
            if (len == 6) {
                throw new PloopyException("OOPS!!! "
                        + "The index of delete cannot be empty.");
            } else {
                try {
                    int ny = Integer.parseInt(fullCommand.substring(7, len));
                    return new DeleteCommand(ny);
                } catch (NumberFormatException e) {
                    throw new PloopyException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
        } else {
            throw new PloopyException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
