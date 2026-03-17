package ploopy;

import ploopy.command.AddCommand;
import ploopy.command.Command;
import ploopy.command.DeleteCommand;
import ploopy.command.ExitCommand;
import ploopy.command.FindCommand;
import ploopy.command.ListCommand;
import ploopy.command.MarkCommand;
import ploopy.task.Deadline;
import ploopy.task.Event;
import ploopy.task.Task;
import ploopy.task.ToDo;

/**
 * Parses user input strings and returns executable command objects.
 */
public class Parser {

    /**
     * Returns the command object represented by the given full command string.
     *
     * @param fullCommand The user input string.
     * @return The parsed command.
     * @throws PloopyException If the command is invalid.
     */
    public static Command parse(String fullCommand) throws PloopyException {
        String trimmed = fullCommand.trim();
        String[] parts = trimmed.split("\\s+", 2);
        String commandWord = parts[0];
        String arguments = parts.length > 1 ? parts[1].trim() : "";

        switch (commandWord) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return parseMark(arguments);
        case "unmark":
            return parseUnmark(arguments);
        case "find":
            return parseFind(arguments);
        case "todo":
            return parseTodo(arguments);
        case "deadline":
            return parseDeadline(arguments);
        case "event":
            return parseEvent(arguments);
        case "delete":
            return parseDelete(arguments);
        default:
            throw unknownCommand();
        }
    }

    private static Command parseMark(String arguments) throws PloopyException {
        int index = parseIndex(arguments, "The index of mark cannot be empty.");
        return new MarkCommand(true, index);
    }

    private static Command parseUnmark(String arguments) throws PloopyException {
        int index = parseIndex(arguments, "The index of unmark cannot be empty.");
        return new MarkCommand(false, index);
    }

    private static Command parseFind(String arguments) throws PloopyException {
        requireNonBlank(arguments, "The index of find cannot be empty.");
        return new FindCommand(arguments);
    }

    private static Command parseTodo(String arguments) throws PloopyException {
        requireNonBlank(arguments, "The description of todo cannot be empty.");
        Task task = new ToDo(arguments);
        return new AddCommand(task);
    }

    private static Command parseDeadline(String arguments) throws PloopyException {
        requireNonBlank(arguments, "The time of deadline cannot be empty.");

        int byIndex = arguments.indexOf("/by ");
        if (byIndex == -1) {
            throw unknownCommand();
        }

        String description = arguments.substring(0, byIndex).trim();
        String by = arguments.substring(byIndex + 4).trim();

        requireNonBlank(description, "The description of deadline cannot be empty.");
        requireNonBlank(by, "The time of deadline cannot be empty.");

        Task task = new Deadline(description, by);
        return new AddCommand(task);
    }

    private static Command parseEvent(String arguments) throws PloopyException {
        requireNonBlank(arguments, "The time of event cannot be empty.");

        int fromIndex = arguments.indexOf("/from ");
        int toIndex = arguments.indexOf("/to ");

        if (fromIndex == -1 || toIndex == -1 || fromIndex >= toIndex) {
            throw unknownCommand();
        }

        String description = arguments.substring(0, fromIndex).trim();
        String from = arguments.substring(fromIndex + 6, toIndex).trim();
        String to = arguments.substring(toIndex + 4).trim();

        requireNonBlank(description, "The description of event cannot be empty.");
        requireNonBlank(from, "The start time of event cannot be empty.");
        requireNonBlank(to, "The end time of event cannot be empty.");

        Task task = new Event(description, from, to);
        return new AddCommand(task);
    }

    private static Command parseDelete(String arguments) throws PloopyException {
        requireNonBlank(arguments, "The index of delete cannot be empty.");
        try {
            int index = Integer.parseInt(arguments);
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            throw unknownCommand();
        }
    }

    private static int parseIndex(String arguments, String emptyMessage) throws PloopyException {
        requireNonBlank(arguments, emptyMessage);
        try {
            return Integer.parseInt(arguments) - 1;
        } catch (NumberFormatException e) {
            throw unknownCommand();
        }
    }

    private static void requireNonBlank(String value, String message) throws PloopyException {
        if (value == null || value.isBlank()) {
            throw new PloopyException("OOPS!!! " + message);
        }
    }

    private static PloopyException unknownCommand() {
        return new PloopyException("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
