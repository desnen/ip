package ploopy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import ploopy.command.AddCommand;
import ploopy.command.DeleteCommand;
import ploopy.command.ExitCommand;
import ploopy.command.FindCommand;
import ploopy.command.ListCommand;
import ploopy.command.MarkCommand;

/**
 * Tests for Parser.
 */
public class ParserTest {

    @Test
    public void parse_bye_returnsExitCommand() throws PloopyException {
        assertInstanceOf(ExitCommand.class, Parser.parse("bye"));
    }

    @Test
    public void parse_list_returnsListCommand() throws PloopyException {
        assertInstanceOf(ListCommand.class, Parser.parse("list"));
    }

    @Test
    public void parse_markValidIndex_returnsMarkCommand() throws PloopyException {
        assertInstanceOf(MarkCommand.class, Parser.parse("mark 1"));
    }

    @Test
    public void parse_unmarkValidIndex_returnsMarkCommand() throws PloopyException {
        assertInstanceOf(MarkCommand.class, Parser.parse("unmark 2"));
    }

    @Test
    public void parse_findValidKeyword_returnsFindCommand() throws PloopyException {
        assertInstanceOf(FindCommand.class, Parser.parse("find book"));
    }

    @Test
    public void parse_todoValidDescription_returnsAddCommand() throws PloopyException {
        assertInstanceOf(AddCommand.class, Parser.parse("todo read book"));
    }

    @Test
    public void parse_deadlineValidArguments_returnsAddCommand() throws PloopyException {
        assertInstanceOf(AddCommand.class,
                Parser.parse("deadline submit report /by 2026-03-02"));
    }

    @Test
    public void parse_eventValidArguments_returnsAddCommand() throws PloopyException {
        assertInstanceOf(AddCommand.class,
                Parser.parse("event team meeting /from 2026-03-01 /to 2026-03-02"));
    }

    @Test
    public void parse_deleteValidIndex_returnsDeleteCommand() throws PloopyException {
        assertInstanceOf(DeleteCommand.class, Parser.parse("delete 3"));
    }

    @Test
    public void parse_unknownCommand_throwsException() {
        PloopyException e = assertThrows(PloopyException.class,
                () -> Parser.parse("hello"));
        assertEquals("OOPS!!! I'm sorry, but I don't know what that means :-(",
                e.getMessage());
    }

    @Test
    public void parse_markMissingIndex_throwsException() {
        PloopyException e = assertThrows(PloopyException.class,
                () -> Parser.parse("mark"));
        assertEquals("OOPS!!! The index of mark cannot be empty.",
                e.getMessage());
    }

    @Test
    public void parse_markInvalidIndex_throwsException() {
        PloopyException e = assertThrows(PloopyException.class,
                () -> Parser.parse("mark abc"));
        assertEquals("OOPS!!! I'm sorry, but I don't know what that means :-(",
                e.getMessage());
    }

    @Test
    public void parse_unmarkMissingIndex_throwsException() {
        PloopyException e = assertThrows(PloopyException.class,
                () -> Parser.parse("unmark"));
        assertEquals("OOPS!!! The index of unmark cannot be empty.",
                e.getMessage());
    }

    @Test
    public void parse_findMissingKeyword_throwsException() {
        PloopyException e = assertThrows(PloopyException.class,
                () -> Parser.parse("find"));
        assertEquals("OOPS!!! The index of find cannot be empty.",
                e.getMessage());
    }

    @Test
    public void parse_findBlankKeyword_throwsException() {
        PloopyException e = assertThrows(PloopyException.class,
                () -> Parser.parse("find    "));
        assertEquals("OOPS!!! The index of find cannot be empty.",
                e.getMessage());
    }

    @Test
    public void parse_todoMissingDescription_throwsException() {
        PloopyException e = assertThrows(PloopyException.class,
                () -> Parser.parse("todo"));
        assertEquals("OOPS!!! The description of todo cannot be empty.",
                e.getMessage());
    }

    @Test
    public void parse_todoBlankDescription_throwsException() {
        PloopyException e = assertThrows(PloopyException.class,
                () -> Parser.parse("todo    "));
        assertEquals("OOPS!!! The description of todo cannot be empty.",
                e.getMessage());
    }

    @Test
    public void parse_deadlineMissingArguments_throwsException() {
        PloopyException e = assertThrows(PloopyException.class,
                () -> Parser.parse("deadline"));
        assertEquals("OOPS!!! The time of deadline cannot be empty.",
                e.getMessage());
    }

    @Test
    public void parse_deadlineMissingBy_throwsException() {
        PloopyException e = assertThrows(PloopyException.class,
                () -> Parser.parse("deadline submit report"));
        assertEquals("OOPS!!! I'm sorry, but I don't know what that means :-(",
                e.getMessage());
    }

    @Test
    public void parse_eventMissingArguments_throwsException() {
        PloopyException e = assertThrows(PloopyException.class,
                () -> Parser.parse("event"));
        assertEquals("OOPS!!! The time of event cannot be empty.",
                e.getMessage());
    }

    @Test
    public void parse_eventMissingTo_throwsException() {
        PloopyException e = assertThrows(PloopyException.class,
                () -> Parser.parse("event meeting /from Monday 2pm"));
        assertEquals("OOPS!!! I'm sorry, but I don't know what that means :-(",
                e.getMessage());
    }

    @Test
    public void parse_deleteMissingIndex_throwsException() {
        PloopyException e = assertThrows(PloopyException.class,
                () -> Parser.parse("delete"));
        assertEquals("OOPS!!! The index of delete cannot be empty.",
                e.getMessage());
    }

    @Test
    public void parse_deleteInvalidIndex_throwsException() {
        PloopyException e = assertThrows(PloopyException.class,
                () -> Parser.parse("delete abc"));
        assertEquals("OOPS!!! I'm sorry, but I don't know what that means :-(",
                e.getMessage());
    }

    @Test
    public void parse_commandWithExtraSpaces_stillWorks() throws PloopyException {
        assertInstanceOf(AddCommand.class, Parser.parse("todo    read book"));
    }
}
